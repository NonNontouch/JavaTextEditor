import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextControl {

    private TextArea textArea;
    private TextArea SampleTextArea;
    private TextArea FindTextArea;

    private Stage textStage;
    private GridPane secondaryLayout;
    private GridPane TextFinderLayout;

    private ComboBox<Integer> fontSize;
    private ComboBox<String> fontFamily;

    public TextControl(Stage inputstage, TextArea inputTextArea) {

        this.textArea = inputTextArea;
        this.textStage = inputstage;
        textArea.setFont(Font.font("System", 16));

    }

    public void TextControlEvent() {

        secondaryLayout = new GridPane();
        secondaryLayout.setHgap(15);
        secondaryLayout.setVgap(10);
        secondaryLayout.setAlignment(Pos.CENTER);

        initFields();

        Scene secondScene = new Scene(secondaryLayout, 550, 340);

        // สร้าง stage ใหม่
        Stage textcontrolstage = new Stage();
        textcontrolstage.setTitle("Font");
        textcontrolstage.setScene(secondScene);
        try {
            textcontrolstage.getIcons().add(new Image(new FileInputStream("Picture/Icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // ตั้งให้ว่าถ้าไม่ปิดwindow นี้ก็จะทำอะไรไม่ได้
        textcontrolstage.initModality(Modality.WINDOW_MODAL);

        // ตั้งให้เป็น window ลูก
        textcontrolstage.initOwner(textStage);

        // เซทตำแหน่งให้อยู่ซ้ายบนเสมอ
        textcontrolstage.setX(textStage.getX());
        textcontrolstage.setY(textStage.getY());

        textcontrolstage.setResizable(false);

        textcontrolstage.show();

    }

    private void initFields() {

        SampleTextArea = new TextArea("Sample Text");
        SampleTextArea.setPrefSize(520, 250);
        SampleTextArea.setFont(textArea.getFont());
        SampleTextArea.setEditable(false);

        fontSize = new ComboBox<Integer>();
        for (int i = 8; i <= 72; i++) {
            fontSize.getItems().add(i);
        }
        fontSize.setValue((int) textArea.getFont().getSize());

        secondaryLayout.add(new Label("Font Size: "), 0, 0);
        secondaryLayout.add(fontSize, 0, 1);

        fontSize.setOnAction(e -> {
            String family = textArea.getFont().getFamily();
            double size = fontSize.getSelectionModel().getSelectedItem();
            textArea.setFont(Font.font(family, size));
            SampleTextArea.setFont(Font.font(family, size));
        });

        fontFamily = new ComboBox<String>();
        fontFamily.getItems().addAll(Font.getFamilies());
        fontFamily.setMaxWidth(150);
        fontFamily.setValue(textArea.getFont().getFamily());

        secondaryLayout.add(new Label("Font Family: "), 1, 0);
        secondaryLayout.add(fontFamily, 1, 1);

        fontFamily.setOnAction(e -> {
            String family = fontFamily.getSelectionModel().getSelectedItem();
            double size = textArea.getFont().getSize();
            textArea.setFont(Font.font(family, size));
            SampleTextArea.setFont(Font.font(family, size));
        });

        secondaryLayout.add(SampleTextArea, 0, 2, 2, 1);

    }

    public void TextFinderEvent() {
        TextFinderLayout = new GridPane();
        
        TextFinderLayout.setAlignment(Pos.CENTER);

        FindText();

        Scene thirdScene = new Scene(TextFinderLayout, 550, 340);

        // สร้าง stage ใหม่
        Stage textcontrolstage = new Stage();
        textcontrolstage.setTitle("Find");
        textcontrolstage.setScene(thirdScene);


        // ตั้งให้เป็น window ลูก
        textcontrolstage.initOwner(textStage);

        // เซทตำแหน่งให้อยู่ซ้ายบนเสมอ
        textcontrolstage.setX(textStage.getX());
        textcontrolstage.setY(textStage.getY());

        textcontrolstage.setResizable(false);

        textcontrolstage.show();
    }

    private void FindText() {
        if (textArea.getText() != null) {
            Button findbtn = new Button("Find");
            Button nextbtn = new Button("Next");
            Button previousbtn = new Button("previous");
            TextArea FindArea = new TextArea();
            FindArea.setPrefSize(100, 20);
            Label finetextLabel = new Label("Input Your text");
            FindTextArea = new TextArea();
            TextFinderLayout.add(finetextLabel, 0, 0);
            TextFinderLayout.add(FindArea, 1, 0);
            TextFinderLayout.add(findbtn,0,1);
            TextFinderLayout.add(nextbtn,1,1);
            TextFinderLayout.add(previousbtn,2,1);


        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Your TextArea is null");
            alert.show();
        }
    }

}