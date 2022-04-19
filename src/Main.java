import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Button btOpen = new Button("Open");

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        TextField panetf = new TextField();
        panetf.setPrefHeight(500);
        FileIO fileio = new FileIO(primaryStage, panetf);

        UI ui = new UI();
        ui.setTop(btOpen);
        ui.setCenter(panetf);

        btOpen.setAlignment(Pos.TOP_LEFT);
        btOpen.setOnAction(e -> fileio.OpenFile());

        Scene scene = new Scene(ui, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}