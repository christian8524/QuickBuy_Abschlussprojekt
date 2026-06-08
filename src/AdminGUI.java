import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminGUI {

    private Admin admin;

    private ListView<String> produktListe;

    public AdminGUI(Admin admin) {
        this.admin = admin;
    }

    public void show(Stage stage) {

        // Produktliste
        produktListe = new ListView<>();

        // Eingabefelder
        TextField nameField = new TextField();
        nameField.setPromptText("Produktname");

        TextField preisField = new TextField();
        preisField.setPromptText("Preis");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        // Buttons
        Button createButton = new Button("Produkt erstellen");
        Button updateButton = new Button("Produkt bearbeiten");
        Button deleteButton = new Button("Produkt löschen");
        Button zurückButton = new Button("Zurück");

        // Vorhandene Produkte laden
        ladeProdukte();

        // Produkt erstellen
        createButton.setOnAction(e -> {

            try {

                String name = nameField.getText();
                float preis = Float.parseFloat(preisField.getText());
                int id = Integer.parseInt(idField.getText());

                admin.createProduct(name, preis, id);

                ladeProdukte();

                nameField.clear();
                preisField.clear();
                idField.clear();

                zeigeMeldung("Erfolg", "Produkt wurde erstellt.");

            } catch (Exception ex) {

                zeigeMeldung("Fehler", "Ungültige Eingabe");
            }
        });

        // Produkt bearbeiten
        updateButton.setOnAction(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String neuerName = nameField.getText();
                float neuerPreis = Float.parseFloat(preisField.getText());

                admin.updateProduct(id, neuerName, neuerPreis);

                ladeProdukte();

                zeigeMeldung("Erfolg", "Produkt wurde bearbeitet.");

            } catch (Exception ex) {

                zeigeMeldung("Fehler", "Ungültige Eingabe");
            }
        });

        // Produkt löschen
        deleteButton.setOnAction(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                admin.deleteProduct(id);

                ladeProdukte();

                zeigeMeldung("Erfolg", "Produkt wurde gelöscht.");

            } catch (Exception ex) {

                zeigeMeldung("Fehler", "Ungültige ID");
            }
        });

        // Zurück zum Hauptmenü
        zurückButton.setOnAction(e -> {

            ModeController controller =
                    new ModeController(admin.getManager());

            new ModeGUI(controller).show(stage);
        });

        // Layout erstellen
        VBox layout = new VBox(
                10,
                new Label("Produkte"),
                produktListe,
                nameField,
                preisField,
                idField,
                createButton,
                updateButton,
                deleteButton,
                zurückButton
        );

        layout.setAlignment(Pos.CENTER);

        // Fenster erstellen
        Scene scene = new Scene(layout, 500, 600);

        stage.setTitle("QuickBuy - Adminbereich");
        stage.setScene(scene);
        stage.show();
    }

    // Alle Produkte anzeigen
    private void ladeProdukte() {

        produktListe.getItems().clear();

        for (int i = 0; i < admin.getAllProducts().size(); i++) {

            Product p = admin.getAllProducts().get(i);

            produktListe.getItems().add(
                    "ID: " + p.getId()
                            + " | "
                            + p.getName()
                            + " | "
                            + p.getPreis() + " €"
            );
        }
    }

    // Meldung anzeigen
    private void zeigeMeldung(String titel, String text) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}