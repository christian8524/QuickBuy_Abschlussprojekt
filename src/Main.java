import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ProductManager manager = new ProductManager();
        ModeController controller = new ModeController(manager);


        new ModeGUI(controller).show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

