package DnDCharacterSheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * This class handles everything in the main charactersheet GUI.
 * On loading a new character need to perform check if file has been provided,
 * then use initialize method to initialize the character sheet with loaded in values.
 */
public class CharacterSheetController {

    private CharacterSheet characterSheet = new CharacterSheet();

    //for testing only
    ObservableList<Spell> list = FXCollections.observableArrayList(
            new Spell("Burning Hands", "Test", 0, 1, 0, "Conjuration", "1 Round", "30 Yd", "Cha", "Ash"),
            new Spell("Arctic Armor", "Test", 0, 1, 0, "Conjuration", "1 Round", "30 Yd", "Cha", "Ash"),
            new Spell("Dazzling Light", "Test", 0, 1, 0, "Conjuration", "1 Round", "30 Yd", "Cha", "Ash")
    );


    // declare fields for saving throws
    @FXML TextField strSavingThrow;
    @FXML TextField dexSavingThrow;
    @FXML TextField conSavingThrow;
    @FXML TextField intSavingThrow;
    @FXML TextField wisSavingThrow;
    @FXML TextField chaSavingThrow;
    @FXML TextField profBonus;
    // declare radiobuttons for savingthrows
    @FXML RadioButton strRadioButton;
    @FXML RadioButton dexRadioButton;
    @FXML RadioButton conRadioButton;
    @FXML RadioButton intRadioButton;
    @FXML RadioButton wisRadioButton;
    @FXML RadioButton chaRadioButton;

    // Declare fields for currency
    @FXML TextField currencyCP;
    @FXML TextField currencySP;
    @FXML TextField currencyEP;
    @FXML TextField currencyGP;
    @FXML TextField currencyPP;
    // Declare up buttons for currency
    @FXML Button buttonCPUp;
    @FXML Button buttonSPUp;
    @FXML Button buttonEPUp;
    @FXML Button buttonGPUp;
    @FXML Button buttonPPUp;
    // Declare down buttons for currency
    @FXML Button buttonCPDown;
    @FXML Button buttonSPDown;
    @FXML Button buttonEPDown;
    @FXML Button buttonGPDown;
    @FXML Button buttonPPDown;

    @FXML GridPane spellLevel0Grid;
    @FXML GridPane spellLevel1Grid;
    @FXML GridPane spellLevel2Grid;
    @FXML GridPane spellLevel3Grid;
    @FXML GridPane spellLevel4Grid;
    @FXML GridPane spellLevel5Grid;
    @FXML GridPane spellLevel6Grid;
    @FXML GridPane spellLevel7Grid;
    @FXML GridPane spellLevel8Grid;
    @FXML GridPane spellLevel9Grid;


    ArrayList<HBox> spellLevel0HBoxList = new ArrayList<>();

    /**
     * FXML function that initializes values on the GUI when loaded.
     */
    @FXML
    private void initialize() {
        dynamicSpellAdder(spellLevel0Grid);
        dynamicSpellAdder(spellLevel1Grid);
        dynamicSpellAdder(spellLevel2Grid);
        dynamicSpellAdder(spellLevel3Grid);
        dynamicSpellAdder(spellLevel4Grid);
        dynamicSpellAdder(spellLevel5Grid);
        dynamicSpellAdder(spellLevel6Grid);
        dynamicSpellAdder(spellLevel7Grid);
        dynamicSpellAdder(spellLevel8Grid);
        dynamicSpellAdder(spellLevel9Grid);

    }

    /**
     * Controller function that uses reflection to find the TextField typed into and update the model based upon the
     * name of the TextField. Calls the filter method to disallow invalid entries for certain fields and then updates
     * the TextBox based on what was allowed.
     * @param event
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void SetValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        /**************************
         **** DEFINE VARIABLES ****
         **************************/
        // Used to reset the caret position after filter.
        int caretPos = ((TextInputControl) event.getSource()).getCaretPosition();

        // Get the id of the control
        String id = ((Control) event.getSource()).getId();
        // Get the value in the field
        String text = ((TextInputControl) event.getSource()).getText();

        // The field name and data type are separated by an underscore
        String[] values = id.split("_", 3);
        // The field is the first value,
        String field = values[0];
        // And the datatype is the second.
        String dataType = values[1];
        // Get the other instance of the field if it exists
        boolean instance = false;
        if (values.length > 2) {
            instance = !Boolean.parseBoolean(values[2]);
        }

        // Get the field we're editing
        Field targetField = null;
        Field subclassField = null;
        // Check to see if we're referencing a subclass
        // The field name and data type are separated by an underscore
        String[] subclasses = field.split("-", 2);

        /**************************
         *** GET SELECTED FIELD ***
         **************************/
        if (subclasses.length > 1) {
            System.out.println("Set " + subclasses[0] + "." + subclasses[1] + ".");
            // The subclass is the first value,
            String subclass = subclasses[0];
            field = subclasses[1];
            // And its field is the second.
            subclassField = (CharacterSheet.class.getDeclaredField(subclass));
            targetField = (CharacterSheet.class.getDeclaredField(subclass)).getType().getDeclaredField(field);
        } else {
            // Get the field we're editing
            targetField = CharacterSheet.class.getDeclaredField(field);
        }


        // Filter text values
        text = Filter(subclassField, targetField, text, dataType);

        /**************************
         *** SET ALL CONTROL VALS *
         **************************/
        Scene scene = ((Control) event.getSource()).getScene();

        // Assign value to original field.
        ((TextInputControl) event.getSource()).setText(text);

        if (values.length > 2) {
            // Set the other field. (Use !instance to find the other field.)
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + instance)).setText(text);
        }

        // Reposition the caret
        ((TextInputControl) event.getSource()).positionCaret(caretPos);

        Save(id, text);

    }

    /**
     * Saves a variable to the save file.
     * @param id
     * @param value
     */
    public static void Save(String id, String value){
        /**************************
         ****** SAVE TO FILE ******
         **************************/
        String path = "save.txt";
        System.out.println("Save " + id + " as " + value);
        try {
            String line = "";
            String save = "";
            BufferedReader bufferreader = new BufferedReader(new FileReader(path));

            // If we have already saved this value to a file, we need to replace the value.
            while ((line = bufferreader.readLine()) != null) {
                if (!line.contains(id))
                    save += line + "\n";
            }

            // Save current content
            save += id + "=" + value + "\n";

            // Get file reader that accesses save
            FileWriter file = new FileWriter(path);

            // Save data
            file.write(save);

            // Close file writer
            file.flush();
            file.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads text values from save into fields.
     * @param scene
     */
    public static void Load(Scene scene){
        /**************************
         **** LOAD FROM FILE ******
         **************************/
        String path = "save.txt";
        try {
            String line = "";
            BufferedReader bufferreader = new BufferedReader(new FileReader(path));

            // For each line in the save file, set values in character creator and the gui.
            while ((line = bufferreader.readLine()) != null) {
                // Get ID and value
                String[] values = line.split("=");

                // Set text field if value is not null
                if (values.length > 1){
                    if (values[1] != null & values[1] != "")
                        ((TextField) scene.lookup("#" + values[0])).setText(values[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves an additional value to an array of saved values.
     * for saving inventory items. NOT for use in SetArray.
     * @param id
     * @param value
     */
    public static void SaveInventory(String id, String value){
        /**************************
         ****** SAVE TO FILE ******
         **************************/
        String path = "inventory.txt";
        try {
            String line = "";
            String save = "";
            value = "\"" + value + "\""; // Values have to be in quotes.
            BufferedReader bufferreader = new BufferedReader(new FileReader(path));

            boolean idFound = false; // Does the array already exist?

            // For each line in the file,
            while ((line = bufferreader.readLine()) != null) {
                // find the location of the target array
                if (line.contains(id)) {
                    line = line.replace(value,"");
                    line += "," + value;
                    idFound = true; // the category exists
                }
                // Save modified line to file
                save += line + "\n";
            }

            // If the array does not already exist, create it.
            if (!idFound) save += id + "=" + value;

            // Get file reader that accesses save
            FileWriter file = new FileWriter(path);

            // Save data
            file.write(save);

            // Close file writer
            file.flush();
            file.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads inventory values from lists into the sheet from a database. Locates items by name.
     * @param scene
     */
    public static void LoadInventory(Scene scene, CharacterSheet characterSheet){
        /**************************
         **** LOAD FROM FILE ******
         **************************/
        String path = "inventory.txt";
        try {
            String line = "";
            BufferedReader bufferreader = new BufferedReader(new FileReader(path));

            ArrayList<Item> items = new ArrayList<Item>();
            ArrayList<Weapon> weapons = new ArrayList<Weapon>();
            ArrayList<Armor> armor = new ArrayList<Armor>();

            ArrayList<Item> list = new ArrayList<Item>();

            // For each line in the save file, set values in character creator and the gui.
            while ((line = bufferreader.readLine()) != null) {
                // Separate ID and array
                String[] values = line.split("=");

                // Load inventory if value is not null
                if (values.length > 1){
                    list = SQLiteHandler.LoadItemsFromSave(SQLiteHandler.Setup(), values[0] , values[1]);

                    if (list.get(0).getClass() == Weapon.class){
                        // LOAD LIST FROM SQL
                        for(int i = 0; i < list.size(); i++)
                            weapons.add((Weapon)list.get(i));
                    }
                    if (list.get(0).getClass() == Armor.class){
                        // LOAD LIST FROM SQL
                        for(int i = 0; i < list.size(); i++)
                            armor.add((Armor)list.get(i));
                    }
                    else{
                        items.addAll(list);
                    }
                }
            }

            characterSheet.getInventory().setItems(items);
            characterSheet.getInventory().getCharEquipment().setWeaponList(weapons);
            characterSheet.getInventory().getCharEquipment().setArmorList(armor);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Controller helper function that filters text entered into TextFields and returns the filtered text/value.
     * Uses reflection to determine datatype from TextField on GUI and then switches based on the datatype declared in
     * that name.
     * @param subclass
     * @param field
     * @param text
     * @param dataType
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public String Filter(Field subclass, Field field, String text, String dataType) throws IllegalAccessException, NoSuchFieldException {

        field.setAccessible(true);
        Object value = null;
        String element = "";
        // Get array element
        if (dataType.contains("array")) {
            element = dataType.split("-")[1];
            dataType = dataType.split("-")[0];
        }

        System.out.println("Data type: " + dataType + "; fieldName: " + field.getName());
        // Ensure text meets requirements
        switch (dataType) {
            case "int":
                // Remove all non-numbers..
                // if(!text.equals("-")) {
                text = text.replaceFirst("[^\\d-]", "");
                if ((text.length() > 1)) {
                    String textsub = text.substring(1);
                    text = text.substring(0, 1) + textsub.replaceAll("[^\\d]", "");
                }
                System.out.println("Text is: " + text);
                // Make sure there is at least one number in the field
                if (!text.equals("") && !text.equals("-")) {
                    value = Integer.parseInt(text);
                    // }
                }
                break;
            case "float":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d.]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")) {
                    value = Float.parseFloat(text);
                }
                break;
            case "str":
                value = text;
                break;
        }

        if (value != null) {
            text = value.toString();
            // Set Value
            if (subclass != null & value != null) {
                // Make the subclass accessible if it's not null
                subclass.setAccessible(true);
                // Make a copy of the subclass variable within CharacterSheet
                //System.out.println("Subclass: " + subclass.getName());
                Object obj = subclass.get(characterSheet);
                //System.out.println("obj: " + obj.toString());
                // Change the subclass instantiation to have our new value
                field.set(obj, value);
                // Reset the subclass instantiation within character sheet
                subclass.set(characterSheet, obj);
            } else {
                field.set(characterSheet, value);
            }
        }

        return text;
    }

    /**
     * Special Controller function to set the values in the model for arrays.
     * Does not call filter to filter the text - performs that operation internally since the only
     * datatype for these is int. Determines which array based upon reflection and the TextField ID in the
     * FXML. DOES NOT support any 2D array except for Stats.
     * @param event
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void SetArrayValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException {
        //subclass-field_element1-element2

        /* *************************
         **** DEFINE VARIABLES ****
         **************************/
        // Used to reset the caret position after filter.
        Scene scene = ((Control) event.getSource()).getScene();
        int caretPos = ((TextInputControl) event.getSource()).getCaretPosition();
        String id = ((Control) event.getSource()).getId(); // Get the id of the control
        String text = ((TextInputControl) event.getSource()).getText(); // Get the value in the field
        String[] values = id.replace("array-", "").split("_", 3); // The field name and data type are separated by an underscore
        String field = values[0]; // The field is the first value,
        String subclass = field.split("-")[0];
        field = field.split("-")[1];
        int value = 0;

        // Get the value to assign
        // Remove all non-numbers..
        text = text.replaceAll("[^\\d]", "");

        // Make sure there is at least one number in the field
        if (text != "" && text != null) {


            if (!text.equals("")) {
                value = Integer.parseInt(text);
            }

            // And the datatype is the second.
            String[] elements = values[1].split("-");
            // Set 2-Dimensional Array
            if (elements.length > 1) {
                characterSheet.setStat(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), value);

                // ID's of total and mod
                String total = subclass + "-" + field + "_" + elements[0] + "-" + "0";
                String mod = subclass + "-" + field + "_" + elements[0] + "-" + "1";

                // Calculated values of total and mod
                String modVal   = String.valueOf(characterSheet.getStats().getStat(Integer.parseInt(elements[0]), 1));
                String totalVal = String.valueOf(characterSheet.getStats().getStat(Integer.parseInt(elements[0]), 0));

                // now that it's set let's update the mod
                ((TextField) scene.lookup("#" + mod)).setText(String.valueOf(modVal));
                // now update the total
                ((TextField) scene.lookup("#" + total)).setText(String.valueOf(totalVal));
                // now update saving throws
                updateSavingThrows();
                // Save to be loaded in later
                Save(mod, modVal); // Save mod
                // Save total to be loaded in later
                Save(total, totalVal); // Save total
            }
            // Set 1-Dimensional Array
            else {
                Field targetField = (CharacterSheet.class.getDeclaredField(subclass)).getType().getDeclaredField(field);
                targetField.setAccessible(true);

                // Set Value
                if (subclass != null) {
                    Field subclassField = (CharacterSheet.class.getDeclaredField(subclass));
                    subclassField.setAccessible(true);

                    // Get value of target field
                    int[] temp = (int[]) targetField.get(subclassField.get(characterSheet));
                    // Change the value of the copy
                    temp[Integer.parseInt(elements[0])] = value;
                    // Set the target field to hold the copied object
                    targetField.set(subclassField.get(characterSheet), temp);

                } else {
                    // Make a copy of the target field within CharacterSheet
                    int[] temp = (int[]) targetField.get(characterSheet);
                    // Set the target field to hold our new value
                    temp[Integer.parseInt(elements[0])] = value;
                    // Set the field to hold the copy object
                    targetField.set(characterSheet, temp);
                }
            }
        }
        System.out.println("Misc stats AC value: " + characterSheet.getMiscStats().getAC()[3]);

        // Assign value to original field.
        ((TextInputControl) event.getSource()).setText(text);

        // Reposition the caret
        ((TextInputControl) event.getSource()).positionCaret(caretPos);

        // Save values
        Save(id, Integer.toString(value)); // Save value
    }

    /**
     * Controller method only for the HP section of the gui.
     * Calls the setValue method to update the values in the model.
     * Updates the progress bar to give visual representation of HP value
     * and calls various helper functions to calculate damage, healing, etc.
     * @param event
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    public void setHP(KeyEvent event) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {

        Scene scene = ((Control) event.getSource()).getScene();
        String id = ((Control) event.getSource()).getId();

        // Check if we're dealing damage, because that's a different thing entirely
        if (!id.equals("damagefield")) {
            // set the value in the GUI
            SetValue(event);
            // if we're NOT typing in the currentHP box, make sure it gets updated
            if (!id.equals("hitPoints-currentHP_int")) {
                ((TextField) scene.lookup("#hitPoints-currentHP_int")).setText(String.valueOf(characterSheet.getHp().getCurrentHP()));
            }

            if (!id.equals("#hitPoints-tempHP_int")) {
                // ((TextField) scene.lookup("#hitPoints-tempHP_int")).setText(String.valueOf(characterSheet.getHp().getTempHP()));
            }
        } else if (id.equals("damagefield")) {

            // basically writing a truncated version of setValue with Filter here
            int caretPos = ((TextInputControl) event.getSource()).getCaretPosition();
            String text = ((TextInputControl) event.getSource()).getText();

            // make sure it's not blank to avoid errors
            if (text != "" && text != null) {
                //Check to see if we're entering a negative number
                // Allow a dash for the first character
                text = text.replaceFirst("[^\\d-]", "");
                // If the length of the string is greater than 1, then replace everything NOT an integer
                if ((text.length() > 1)) {
                    String textsub = text.substring(1);
                    text = text.substring(0, 1) + textsub.replaceAll("[^\\d]", "");
                }

                System.out.println("Text is: " + text);

                ((TextField) scene.lookup("#damagefield")).setText(text);
                ((TextInputControl) event.getSource()).positionCaret(caretPos);


                if (event.getCode() == KeyCode.ENTER) {
                    // set the current hp
                    characterSheet.getHp().changeCurrentHP(Integer.parseInt(text));
                    // clear the damage field
                    ((TextField) scene.lookup("#damagefield")).setText("");
                    // set the temp HP in case they had temp HP
                    ((TextField) scene.lookup("#hitPoints-tempHP_int")).setText(String.valueOf(characterSheet.getHp().getTempHP()));
                    // set the current HP in case they did not have temp HP
                    ((TextField) scene.lookup("#hitPoints-currentHP_int")).setText(String.valueOf(characterSheet.getHp().getCurrentHP()));
                }
            }
        }
        ((ProgressBar) scene.lookup("#hpBar")).setProgress((float) Math.abs(Math.abs(characterSheet.getHp().getBleedOut()) + characterSheet.getHp().getCurrentHP()) / (Math.abs(characterSheet.getHp().getBleedOut()) + characterSheet.getHp().getMaxHP()));
    }

    /**
     * Controller method to add and remove rows and nodes from the spell lists dynamically
     * Note that this method DOES NOT DELETE the row of thr gridpane, only hides it.
     * When spells are added, they should be tracked in the model in accordance with how they are in the GUI
     * and then then cleaned up on save and load.
     * Ex: player adds two spells - gridpane has rows 0, 1, 2 - spell array has spells 0, 1, 2
     * player deleted spell, number 1. gridpane has rows 0, 1 (now hidden), 2 - spell array has spells 0, 1 (now set to null), 2.
     * When sheet is saved, save method steps through spell arraylist and saves ONLY spells != null.
     * @param spellgrid
     */
    public void dynamicSpellAdder(GridPane spellgrid) {

        int rowindex = spellgrid.getRowCount();

        // add new items to list
        spellgrid.add(new TextField(), 0, rowindex, 1, 1);
        spellgrid.add(new TextField(), 1, rowindex, 1, 1);
        spellgrid.add(new ComboBox<>(), 2, rowindex, 1, 1);
        spellgrid.add(new Button("X"), 3, rowindex, 1, 1);

        // get all the fields we're currently working with to format them
        TextField currentPrep = (TextField) getNodeFromGridPane(spellgrid, 0, rowindex);
        TextField currentUsed = (TextField) getNodeFromGridPane(spellgrid, 1, rowindex);
        ComboBox prevbox = (ComboBox) getNodeFromGridPane(spellgrid, 2, rowindex - 1);
        ComboBox currentbox = (ComboBox) getNodeFromGridPane(spellgrid, 2, rowindex);
        Button currentbutton = (Button) getNodeFromGridPane(spellgrid, 3, rowindex);

        //format the items
        currentbox.setItems(list);
        currentbox.setEditable(true);
        currentbox.setPrefWidth(770);
        currentbox.setMaxWidth(1.7976931348623157E308);
/*
        currentbox.setConverter(new StringConverter<Spell>(){
            @Override
            public String toString(Spell spell) {
                if (spell == null) return null;
                else {
                    return spell.getName();
                }
            }

            @Override
            public Spell fromString(String s) {
                System.out.println("The String inside fromString is: " + s);
               // return new Spell("test", "test", 0, 0, 0, "test", "test", "test", "test", "test");
                for (Spell o: list) {
                    if(o.getName().equals(s)){
                        return o;
                    }
                }
                return null;
            }
        });
*/
        currentbox.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {

            if (currentbox.getSelectionModel().getSelectedItem() != null && list.contains(currentbox.getSelectionModel().getSelectedItem())){
                dynamicSpellAdder(spellgrid);
            }


            System.out.println(currentbox.getSelectionModel().getSelectedItem());
            System.out.println("observablevalue is : " + observableValue);
            System.out.println("o is: " + o);
            System.out.println("t1 is: " + t1.toString());
            System.out.println("getValue is: " + currentbox.getValue());

        });


        // Tell the combobox the format to display the items in
        currentbox.setCellFactory(new SpellCellFactory());
        // Tell the button area the format to display the items in
        currentbox.setButtonCell(new SpellCell());


        if(prevbox != null){

            // When a new box is populated, make sure the old one is not editable
            prevbox.setEditable(false);
            // Make sure the old one is also disabled
            prevbox.setDisable(true);
            // Set the opacity of the box so that the labels inside are visible
            // Note that this does not change the opacity of the labels themselves
            prevbox.setStyle("-fx-opacity: 1");

        }

        currentbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if((getNodeFromGridPane(spellgrid, 2, GridPane.getRowIndex((Node) e.getSource()))).isDisabled()) {
                    spellgrid.getChildren().removeIf(node -> (GridPane.getRowIndex(node) != null) && (GridPane.getRowIndex(node) == rowindex));
                }
            }
        });
    }

    /**
     * Controller helper method to find the node in the GridPane object given the row and the column.
     * Note that Java does not provide the functionality natively.
     * @param gridPane
     * @param col
     * @param row
     * @return
     */
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        ObservableList<Node> children = gridPane.getChildren();

        // step through the entire list of children and check
        // No, finding a node by its location in a gridpane is not natively supported by Java for some reason
        for (Node node : children) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;

    }

    /**
     * Controller method that checks if the radio button for the saving throw has been toggled
     * if it has, it updates the textbox with the stat modifier plus the proficiency bonus
     * if not, then it updates it with the modifier only.
     * Values do not need to be set in the model since they are calculated from component values.
     */
    public void updateSavingThrows(){

        if(strRadioButton.isSelected()) strSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[0] + characterSheet.getProficiency()));
        else strSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[0]));

        if(dexRadioButton.isSelected()) dexSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[1] + characterSheet.getProficiency()));
        else dexSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[1]));

        if(conRadioButton.isSelected()) conSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[2] + characterSheet.getProficiency()));
        else conSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[2]));

        if(intRadioButton.isSelected()) intSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[3] + characterSheet.getProficiency()));
        else intSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[3]));

        if(wisRadioButton.isSelected()) wisSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[4] + characterSheet.getProficiency()));
        else wisSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[4]));

        if(chaRadioButton.isSelected()) chaSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[5] + characterSheet.getProficiency()));
        else chaSavingThrow.setText(String.valueOf(characterSheet.getSavingThrows()[5]));
    }

    /**
     * Controller method that updates the value of the proficiency bonus via setValue method
     * and then updates the saving throws to reflect the change via updateSavingThrow method.
     * @param event
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    public void updateProf(KeyEvent event) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        SetValue(event);
        updateSavingThrows();
    }

    /**
     * Controller function that enables buttons in currency box to increment and decrement currency.
     * Updates the textboxes with the value of currency when called.
     * @param event
     */
    public void changeCurrency (ActionEvent event){
        // Button functionality for CP
        if(event.getSource() == buttonCPUp) {
            characterSheet.getCurrency().incCp();
            currencyCP.setText(String.valueOf(characterSheet.getCurrency().getCp()));
        }
        if(event.getSource() == buttonCPDown) {
            characterSheet.getCurrency().decCp();
            currencyCP.setText(String.valueOf(characterSheet.getCurrency().getCp()));
        }
        // Button functionality for SP
        if(event.getSource() == buttonSPUp) {
            characterSheet.getCurrency().incSp();
            currencySP.setText(String.valueOf(characterSheet.getCurrency().getSp()));
        }
        if(event.getSource() == buttonSPDown) {
            characterSheet.getCurrency().decSp();
            currencySP.setText(String.valueOf(characterSheet.getCurrency().getSp()));
        }
        // Button functionality for GP
        if(event.getSource() == buttonGPUp) {
            characterSheet.getCurrency().incGp();
            currencyGP.setText(String.valueOf(characterSheet.getCurrency().getGp()));
        }
        if(event.getSource() == buttonGPDown) {
            characterSheet.getCurrency().decGp();
            currencyGP.setText(String.valueOf(characterSheet.getCurrency().getGp()));
        }
        // Button functionality for EP
        if(event.getSource() == buttonEPUp) {
            characterSheet.getCurrency().incEp();
            currencyEP.setText(String.valueOf(characterSheet.getCurrency().getEp()));
        }
        if(event.getSource() == buttonEPDown) {
            characterSheet.getCurrency().decEp();
            currencyEP.setText(String.valueOf(characterSheet.getCurrency().getEp()));
        }
        // Button functionality for PP
        if(event.getSource() == buttonPPUp) {
            characterSheet.getCurrency().incPp();
            currencyPP.setText(String.valueOf(characterSheet.getCurrency().getPp()));
        }
        if(event.getSource() == buttonPPDown) {
            characterSheet.getCurrency().decPp();
            currencyPP.setText(String.valueOf(characterSheet.getCurrency().getPp()));
        }
    }

    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    /**
     * Add an item to the inventory based on its category.
     * Save changes and update tables.
     * @param event
     * @throws Exception
     */
    public void AddItem(MouseEvent event) throws Exception {
        // Get current scene
        Scene scene = ((Button)event.getSource()).getScene();

        // Get selected item to add
        Item item = (Item)((TableView)scene.lookup("#TableShop")).getSelectionModel().getSelectedItem();

        // If there is a selected item
        if (item != null) {
            // Add item to proper list depending on its category
            switch (item.getCategory()) {
                case "Weapon":
                    ArrayList<Weapon> weapons = characterSheet.getInventory().getCharEquipment().getWeaponList();
                    weapons.add((Weapon) item);
                    characterSheet.getInventory().getCharEquipment().setWeaponList(weapons);
                    break;
                case "Armor":
                    //ArrayList<Armor> armors = characterSheet.getInventory().getCharEquipment().getArmorList();
                    //armors.add((Armor)item);
                    //characterSheet.getInventory().getCharEquipment().setArmorList(armors);
                    break;
                default:
                    ArrayList<Item> items = characterSheet.getInventory().getItems();
                    items.add(item);
                    characterSheet.getInventory().setItems(items);
                    break;
            }

            // Save item to file
            SaveInventory(item.getCategory(), item.getName());

            // Show changes
            StartSceneController.UpdateTables(SQLiteHandler.Setup(), scene, characterSheet);
        }

    }

    public void BuyItem(MouseEvent event) throws Exception {

        // Remove Cost

        AddItem(event);
    }
}
