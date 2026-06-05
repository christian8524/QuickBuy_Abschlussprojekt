import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    }

    private void ladeProdukte() {
        produktListe.getItems().clear();

        for (Product product : manager.getAllProducts()) {
            produktListe.getItems().add(
                    "ID: " + product.getId() + " | " + product.getName() + " | " + product.getPreis() + "€"
            );
        }
    }



}
