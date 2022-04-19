import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        UI ui = new UI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}