package DnDCharacterSheet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("startscreen.fxml"));
        primaryStage.setTitle("D&D Character Sheet Editor");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
        //primaryStage.setResizable(false);
    }


    public static void main(String[] args)
    {
        launch(args);

        // JOY'S SQLITE HANDLER TEST!!! Not important, you can delete it if you want.
        try {
            SQLiteHandler.Test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
