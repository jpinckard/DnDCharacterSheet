package DnDCharacterSheet;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class handles everything in the starting scene of the program.
 * ONLY serves as a point for either beginning wizard, opening new character sheet,
 * initializing character sheet from saved values. No other functionality.
 */
public class StartSceneController {

    @FXML
    private Hyperlink blankcharbutton;

    /**
     * Function that opens the charactersheetpane. The end.
     * @param event
     * @throws Exception
     */
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
        //LoadDefaultValues(stage.getScene());
    }

    public void LoadDefaultValues(Scene scene) throws Exception {
        /*
        ComboBox spellComboBox = scene.lookup("#);
        spellComboBox.getItems().clear();
        spellComboBox.setItems(SQLiteHandler.LoadSpells());
         */

        Connection connection = SQLiteHandler.Setup();
        TableView weaponsAndAttacks = (TableView)scene.lookup("#TableWeapons");

        System.out.println("Table: " + weaponsAndAttacks.toString());

        //weaponsAndAttacks.getColumns().clear();
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn costColumn = new TableColumn("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn weightColumn = new TableColumn("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn categoryColumn = new TableColumn("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn damageColumn = new TableColumn("Damage");
        damageColumn.setCellValueFactory(new PropertyValueFactory<>("damage"));

        TableColumn rangeColumn = new TableColumn("Range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        TableColumn martialColumn = new TableColumn("Martial");
        martialColumn.setCellValueFactory(new PropertyValueFactory<>("martial"));

        TableColumn rangedColumn = new TableColumn("Ranged");
        rangedColumn.setCellValueFactory(new PropertyValueFactory<>("ranged"));

        TableColumn finesseColumn = new TableColumn("Finesse");
        finesseColumn.setCellValueFactory(new PropertyValueFactory<>("finesse"));

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        weaponsAndAttacks.getColumns().clear();

        weaponsAndAttacks.getColumns().addAll(nameColumn, costColumn, weightColumn, descriptionColumn, categoryColumn, amountColumn, damageColumn, rangeColumn,
                martialColumn, rangedColumn, finesseColumn, typeColumn);

        ArrayList<Weapon> weapons = SQLiteHandler.LoadWeapons(connection);

        System.out.println(weapons.get(0));

        weaponsAndAttacks.getItems().add(weapons);

    }

}
