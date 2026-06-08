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

        produktListe = new ListView<>();

        TextField nameField = new TextField();
        nameField.setPromptText("Produktname");

        TextField preisField = new TextField();
        preisField.setPromptText("Preis");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        Button createButton = new Button("Produkt erstellen");
        Button updateButton = new Button("Produkt bearbeiten");
        Button deleteButton = new Button("Produkt löschen");

        ladeProdukte();

        // CREATE
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

            } catch (Exception ex) {
                zeigeMeldung("Fehler", "Ungültige Eingabe");
            }
        });

        // UPDATE
        updateButton.setOnAction(e -> {

            try {
                int id = Integer.parseInt(idField.getText());
                String neuerName = nameField.getText();
                float neuerPreis = Float.parseFloat(preisField.getText());

                admin.updateProduct(id, neuerName, neuerPreis);

                ladeProdukte();

            } catch (Exception ex) {
                zeigeMeldung("Fehler", "Ungültige Eingabe");
            }
        });

        // DELETE
        deleteButton.setOnAction(e -> {

            try {
                int id = Integer.parseInt(idField.getText());

                admin.deleteProduct(id);

                ladeProdukte();

            } catch (Exception ex) {
                zeigeMeldung("Fehler", "Ungültige ID");
            }
        });

        VBox layout = new VBox(
                10,
                new Label("Produkte"),
                produktListe,
                nameField,
                preisField,
                idField,
                createButton,
                updateButton,
                deleteButton
        );

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 600);

        stage.setTitle("QuickBuy - Adminbereich");
        stage.setScene(scene);
        stage.show();
    }

    private void ladeProdukte() {

        produktListe.getItems().clear();

        for (Product p : admin.getAllProducts()) {

            produktListe.getItems().add(
                    "ID: " + p.getId()
                            + " | "
                            + p.getName()
                            + " | "
                            + p.getPreis() + " €"
            );
        }
    }

    private void zeigeMeldung(String titel, String text) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
