package DnDCharacterSheet;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Array;
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

    private CharacterSheet characterSheet; // the interface controller
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
        // Retrieve the instance of CharacterSheet that controls the interface.
        characterSheet = ((CharacterSheetController)fxmlLoader.getController()).getCharacterSheet();
        //stage.setResizable(false);
        blankcharbutton.getScene().getWindow().hide();

        // Load default values into text boxes from save.
        LoadDefaultValues(stage.getScene());
    }

    public void LoadDefaultValues(Scene scene) throws Exception {
        // Form a connection with the database
        Connection connection = SQLiteHandler.Setup();
        //////////////////////////
        //// LOAD SAVED VALUES ///
        // Get inventory

        // Get save data
        CharacterSheetController.Load(scene);
        CharacterSheetController.LoadInventory(scene, characterSheet);
        // Update Tables
        UpdateTables(connection, scene, characterSheet);

        ////////////////
        // CATEGORIES //
        ListView categoryList = (ListView)scene.lookup("#ListCategories");
        ArrayList<String> categories = SQLiteHandler.GetCategories(connection);
        System.out.println("First category: " + categories.get(0));
        categoryList.getItems().addAll(categories);

        // Set onclicked event for list elements
        categoryList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String category = categoryList.getSelectionModel().getSelectedItem().toString();
                System.out.println("Category: " + category);
                ArrayList<Item> items = new ArrayList<Item>();
                try {
                    items = SQLiteHandler.Filter(SQLiteHandler.Setup(), category);
                    // Populate table
                    LoadInventoryTable(connection, (TableView)scene.lookup("#TableShop"), items);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("# Items in this category: " + items.size());

            }
        });
    }

    /**
     * Updates tables with information from CharacterSheet.
     * @param connection
     * @param scene
     * @throws Exception
     */
    public static void UpdateTables(Connection connection, Scene scene, CharacterSheet characterSheet) throws Exception {
        // Populate armor table
        LoadArmorTable(connection, (TableView)scene.lookup("#TableArmor"), characterSheet.getInventory().getCharEquipment().getArmorList());
        LoadInventoryTable(connection, (TableView)scene.lookup("#TableInventory"), characterSheet.getInventory().getItems());
        LoadWeaponsTable(connection, (TableView)scene.lookup("#TableWeapons"), characterSheet.getInventory().getCharEquipment().getWeaponList());

        LoadWeaponsTable(connection, (TableView)scene.lookup("#TableWeaponsAndAttacks"), characterSheet.getInventory().getCharEquipment().getWeaponList());
    }

    /**
     * Loads item elements into a table view.
     * @param connection
     * @param table
     * @param items
     */
    public static void LoadInventoryTable(Connection connection, TableView table, ArrayList<Item> items) throws Exception {
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn costColumn = new TableColumn("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn weightColumn = new TableColumn("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        table.getColumns().clear();
        table.getItems().clear();
        table.getColumns().addAll(nameColumn, descriptionColumn, weightColumn, costColumn, amountColumn);
        table.getItems().addAll(items);
    }

    /**
     * Loads item elements into a table view.
     * @param connection
     * @param table
     * @param armor
     */
    public static void LoadArmorTable(Connection connection, TableView table, ArrayList<Armor> armor) throws Exception {
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn costColumn = new TableColumn("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn weightColumn = new TableColumn("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn ACColumn = new TableColumn("AC");
        ACColumn.setCellValueFactory(new PropertyValueFactory<>("baseAC"));
        TableColumn groupColumn = new TableColumn("Group");
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("aGroup"));
        TableColumn stealthDisadvantageColumn = new TableColumn("Stealth Disadvantage");
        stealthDisadvantageColumn.setCellValueFactory(new PropertyValueFactory<>("stealthDisadvantage"));

        table.getColumns().clear();
        table.getColumns().addAll(nameColumn, weightColumn, descriptionColumn, amountColumn, costColumn, ACColumn,  stealthDisadvantageColumn); //groupColumn,
        //ArrayList<Armor> armor = SQLiteHandler.LoadArmor(connection);
        table.getItems().addAll(armor);
    }

    /**
     * Loads item elements into a table view.
     * @param connection
     * @param table
     * @param weapons
     */
    public static void LoadWeaponsTable(Connection connection, TableView table, ArrayList<Weapon> weapons) throws Exception {
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn costColumn = new TableColumn("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn weightColumn = new TableColumn("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

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

        table.getColumns().clear();

        table.getItems().clear();
        table.getColumns().addAll(nameColumn, descriptionColumn, amountColumn, costColumn, weightColumn,  damageColumn, rangeColumn,
                martialColumn, rangedColumn,  typeColumn);
        table.getItems().addAll(weapons);
    }
}
