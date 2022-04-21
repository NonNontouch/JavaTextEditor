import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextControl {

    private TextArea textArea;
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
        secondaryLayout.setHgap(10);
        secondaryLayout.setVgap(5);
        secondaryLayout.setAlignment(Pos.CENTER);

        initFields();

        Scene secondScene = new Scene(secondaryLayout, 270, 100);

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

        fontSize = new ComboBox<Integer>();
        for (int i = 8; i <= 72; i++) {
            fontSize.getItems().add(i);
        }

        secondaryLayout.add(new Label("Font Size: "), 0, 0);
        secondaryLayout.add(fontSize, 0, 1);

        fontSize.setOnAction(e -> {
            String fontFamily = textArea.getFont().getFamily();
            Integer size = fontSize.getSelectionModel().getSelectedItem();
            textArea.setFont(Font.font(fontFamily, size));
        });

        fontFamily = new ComboBox<String>();
        fontFamily.getItems().addAll(Font.getFamilies());
        fontFamily.setMaxWidth(150);

        secondaryLayout.add(new Label("Font Family: "), 1, 0);
        secondaryLayout.add(fontFamily, 1, 1);

        fontFamily.setOnAction(e -> {
            String family = fontFamily.getSelectionModel().getSelectedItem();
            double size = textArea.getFont().getSize();
            textArea.setFont(Font.font(family, size));
        });
    }
}