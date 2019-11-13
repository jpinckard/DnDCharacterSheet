import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Item item;
        //SQLiteHandler.Setup();
        Connection connection = SQLiteHandler.Setup();
        ArrayList<Spell> spells = SQLiteHandler.LoadSpells(connection);

        // Print all spells
        for(int i = 0; i < spells.size(); i++){
            System.out.println(spells.get(i).getName());
        }

        launch(args);
    }
}
