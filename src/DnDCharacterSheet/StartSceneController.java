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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
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

    /**
     * Function that opens the charactersheetpane. The end.
     * @param event
     * @throws Exception
     */
    public void openCharSheetPane (ActionEvent event){

        try {
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
            LoadDefaultValues(stage.getScene());
        } catch (Exception e){
            exceptionPane("Exception in main application caught!", e);
        }
    }

    public void LoadDefaultValues(Scene scene){
        // Form a connection with the database.
        Connection connection = SQLiteHandler.Setup();

        ////////////////
        // CATEGORIES //
        ListView categoryList = (ListView)scene.lookup("#ListCategories");
        try {
            ArrayList<String> categories = SQLiteHandler.GetCategories(connection);
            System.out.println("First category: " + categories.get(0));
            categoryList.getItems().addAll(categories);
        } catch (Exception e){
            exceptionPane("Exception loading categories caught!", e);
        }

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

        // COLUMNS //
        /*
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


        /////////////
        // WEAPONS //
        TableView weaponsTable = (TableView)scene.lookup("#TableWeapons");
        weaponsTable.getColumns().clear();

        weaponsTable.getColumns().addAll(nameColumn, costColumn, weightColumn, descriptionColumn, categoryColumn, amountColumn, damageColumn, rangeColumn,
                martialColumn, rangedColumn,  typeColumn);

        ArrayList<Weapon> weapons = SQLiteHandler.LoadWeapons(connection);
        weaponsTable.getItems().addAll(weapons);


        ///////////////
        // INVENTORY //




        ///////////
        // ARMOR //
        TableView armorTable = (TableView)scene.lookup("#TableArmor");

        TableColumn ACColumn = new TableColumn("AC");
        ACColumn.setCellValueFactory(new PropertyValueFactory<>("baseAC"));
        TableColumn groupColumn = new TableColumn("Group");
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("aGroup"));
        TableColumn stealthDisadvantageColumn = new TableColumn("Stealth Disadvantage");
        stealthDisadvantageColumn.setCellValueFactory(new PropertyValueFactory<>("stealthDisadvantage"));

        armorTable.getColumns().clear();
        armorTable.getColumns().addAll(nameColumn, weightColumn, categoryColumn, descriptionColumn, amountColumn, costColumn, ACColumn,  stealthDisadvantageColumn); //groupColumn,
        ArrayList<Armor> armor = SQLiteHandler.LoadArmor(connection);
        armorTable.getItems().addAll(armor);

         */

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

    public void exceptionPane(String content, Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("There's been an exception!");
        alert.setContentText(content);

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

}
