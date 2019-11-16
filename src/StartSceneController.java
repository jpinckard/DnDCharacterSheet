import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartSceneController {

    @FXML
    private Hyperlink blankcharbutton;

    public void openCharSheetPane (ActionEvent event) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("charactersheetpane.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("D&D Character Sheet Editor");
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
        //stage.setResizable(false);
        blankcharbutton.getScene().getWindow().hide();

    }

}
