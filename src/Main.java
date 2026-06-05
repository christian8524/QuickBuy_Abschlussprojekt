import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ProductManager manager = new ProductManager();
        ModeController controller = new ModeController(manager);

        /*
        War zum testen
        manager.createProduct("Apfel", 1.5f, 1);
        manager.createProduct("Brot", 2.0f, 2);
         */


        new ModeGUI(controller).show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

