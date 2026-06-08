import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Optional;
import java.util.function.Consumer;

import static jdk.internal.org.jline.terminal.Terminal.MouseTracking.Button;

public class ModeGUI {

    private ModeController controller;

    public ModeGUI(ModeController controller) {
        this.controller = controller;
    }

    public void show(Stage stage) {

        Button adminButton = new Button("Admin");
        Button nutzerButton = new Button("Nutzer");

        // ADMIN BUTTON
        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Admin admin = controller.startAdmin();

                new AdminGUI(admin).show(stage);

                System.out.println("Admin-Modus gestartet");
            }
        });

        // NUTZER BUTTON
        nutzerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controller.getManager().isEmpty()) {
                    System.out.println("Bitte zuerst Produkte anlegen!");
                    return;
                }

                // Eingabefenster für Guthaben
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Guthaben");
                dialog.setHeaderText("Startguthaben eingeben:");
                dialog.setContentText("Guthaben:");


                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    String input = result.get();

                    try {
                        float guthaben = Float.parseFloat(input);

                        Nutzer nutzer = controller.startNutzer(guthaben);
                        new NutzerGUI(controller.getManager(), nutzer).show(stage);

                        System.out.println("Nutzer-Modus gestartet");
                    } catch (NumberFormatException ex) {
                        System.out.println("Ungültige Eingabe");
                    }
                }
            }
        });

        VBox layout = new VBox(20, adminButton, nutzerButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("QuickBuy – Modus auswählen");
        stage.setScene(scene);
        stage.show();
    }
}
