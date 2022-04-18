import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Button btOpen = new Button("Open");

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.add(btOpen, 0, 0);
        gridPane.setAlignment(Pos.TOP_LEFT);

        UI ui = new UI();

        FileIO fileio = new FileIO(primaryStage);

        btOpen.setOnAction(e -> fileio.OpenFile());
        borderPane.setCenter(gridPane);
        Scene scene = new Scene(borderPane, 800, 600);
        //Scene scene = new Scene(ui, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}