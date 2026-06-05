import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NutzerGUI {

    private ProductManager manager;
    private Nutzer nutzer;

    private ListView<String> produktListe;
    private ListView<String> warenkorbListe;

    private Label guthabenLabel;
    private Label gesamtLabel;

    public NutzerGUI(ProductManager manager, Nutzer nutzer) {
        this.manager = manager;
        this.nutzer = nutzer;
    }

    public void show(Stage stage) {
        produktListe = new ListView<>();
        warenkorbListe = new ListView<>();

        guthabenLabel = new Label();
        gesamtLabel = new Label();;

        Button inWarenkorbButton = new Button("In Warenkorb legen");
        Button bezahlenButton = new Button("Bezahlen");
        Button warenkorbLeerenButton = new Button("Warenkorb leeren");;

        ladeProdukte();
        updateLabels();

        // Produkt in Warenkorb legen
        inWarenkorbButton.setOnAction(actionEvent -> {
            int index = produktListe.getSelectionModel().getSelectedIndex();

            if (index == -1) {
                zeigeMeldung("Fehler", "Bitte wähle zuerst ein Produkt aus");
                return;
            }

            Product product = manager.getAllProducts().get(index);
            nutzer.addToCart(product);

            ladeWarenkorb();
            updateLabels();

            zeigeMeldung("Erfolg", product.getName() + " wurde in den Warenkorb gelegt.");
        });

        // Warenkorb bezahlen
        bezahlenButton.setOnAction(actionEvent -> {
            if (nutzer.getWarenkorb().isEmpty()) {
                zeigeMeldung("Fehler", "Der Warenkorb ist leer");
                return;
            }

            boolean bezahlt = nutzer.pay();

            if (bezahlt) {
                ladeWarenkorb();
                updateLabels();

                zeigeMeldung(
                        "Bezahlt",
                        "Bezahlung erfolgreich!\nRestguthaben: " + nutzer.getGuthaben() + " €"
                );
            } else {
                zeigeMeldung(
                        "Nicht genug Guthaben",
                        "Dein Guthaben reicht nicht aus.\n" +
                                "Gesamtpreis: " + nutzer.getTotal() + " €\n" +
                                "Guthaben: " + nutzer.getGuthaben() + " €"
                );
            }
        });

        // Warenkorb manuell leeren
        warenkorbLeerenButton.setOnAction(actionEvent -> {
            nutzer.clearCart();
            ladeWarenkorb();
            updateLabels();

            zeigeMeldung("Warenkorb geleert", "Der Warenkorb wurde geleert.");
        });

        Label produktLabel = new Label("Produkte:");
        Label warenkorbLabel = new Label("Warenkorb:");

        VBox layout = new VBox(
                10,
                guthabenLabel,
                produktLabel,
                produktListe,
                inWarenkorbButton,
                warenkorbLabel,
                warenkorbListe,
                gesamtLabel,
                bezahlenButton,
                warenkorbLeerenButton
        );

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 450, 600);
        stage.setTitle("QuickBuy – Nutzerbereich");
        stage.setScene(scene);
        stage.show();
    }

    private void ladeProdukte() {
        produktListe.getItems().clear();

        for (Product product : manager.getAllProducts()) {
            produktListe.getItems().add(
                    "ID: " + product.getId() + " | " + product.getName() + " | " + product.getPreis() + "€"
            );
        }
    }

    private void ladeWarenkorb() {
        warenkorbListe.getItems().clear();

        for (Product product : nutzer.getWarenkorb()) {
            warenkorbListe.getItems().add(
                    product.getName() + " | " + product.getPreis() + "€"
            );
        }
    }

    private void updateLabels() {
        guthabenLabel.setText("Guthaben: " + nutzer.getGuthaben() + " €");
        gesamtLabel.setText("Gesamt im Warenkorb: " + nutzer.getTotal() + " €");
    }

    private void zeigeMeldung(String titel, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

}
