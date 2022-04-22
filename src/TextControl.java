import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextControl {

    private TextArea textArea;
    private TextArea SampleTextArea;

    private Stage textStage;
    private GridPane secondaryLayout;

    private ComboBox<Integer> fontSize;
    private ComboBox<String> fontFamily;

    public TextControl(Stage inputstage, TextArea inputTextArea) {

        this.textArea = inputTextArea;
        this.textStage = inputstage;

    }

    public void TextControlEvent() {

        secondaryLayout = new GridPane();
        secondaryLayout.setHgap(15);
        secondaryLayout.setVgap(10);
        secondaryLayout.setAlignment(Pos.CENTER);

        initFields();

        Scene secondScene = new Scene(secondaryLayout, 450, 300);

        // สร้าง stage ใหม่
        Stage textcontrolstage = new Stage();
        textcontrolstage.setTitle("Font");
        textcontrolstage.setScene(secondScene);

        // ตั้งให้ว่าถ้าไม่ปิดwindow นี้ก็จะทำอะไรไม่ได้
        textcontrolstage.initModality(Modality.WINDOW_MODAL);

        // ตั้งให้เป็น window ลูก
        textcontrolstage.initOwner(textStage);

        // เซทตำแหน่งให้อยู่ซ้ายบนเสมอ
        textcontrolstage.setX(textStage.getX());
        textcontrolstage.setY(textStage.getY());

        textcontrolstage.show();
        

    }

    private void initFields() {

        SampleTextArea = new TextArea("Sample Text");
        SampleTextArea.setPrefSize(200, 150);
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
            String fontFamily = textArea.getFont().getFamily();
            Integer size = fontSize.getSelectionModel().getSelectedItem();
            textArea.setFont(Font.font(fontFamily, size));
            SampleTextArea.setFont(Font.font(fontFamily, size));
            
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

        secondaryLayout.add(SampleTextArea, 0, 2);

    }
}