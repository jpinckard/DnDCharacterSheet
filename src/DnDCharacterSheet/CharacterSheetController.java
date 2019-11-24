package DnDCharacterSheet;

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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;


public class CharacterSheetController {

    CharacterSheet characterSheet = new CharacterSheet();

    //for testing only
    ObservableList<String> list = FXCollections.observableArrayList(
            "Asparagus", "Beans", "Broccoli", "Cabbage", "Carrot",
            "Celery", "Cucumber", "Leek", "Mushroom", "Pepper",
            "Radish", "Shallot", "Spinach", "Swede", "Turnip");


    @FXML TextField strSavingThrow;
    @FXML TextField dexSavingThrow;
    @FXML TextField conSavingThrow;
    @FXML TextField intSavingThrow;
    @FXML TextField wisSavingThrow;
    @FXML TextField chaSavingThrow;
    @FXML TextField profBonus;

    @FXML RadioButton strRadioButton;
    @FXML RadioButton dexRadioButton;
    @FXML RadioButton conRadioButton;
    @FXML RadioButton intRadioButton;
    @FXML RadioButton wisRadioButton;
    @FXML RadioButton chaRadioButton;

    @FXML
    ComboBox spellLevel0Box;
    @FXML
    GridPane spellLevel1Grid;


    ArrayList<HBox> spellLevel0HBoxList = new ArrayList<>();

    /**
     * FXML function that initializes values on the GUI when loaded.
     */
    @FXML
    private void initialize() {
        spellLevel0Box.setItems(list);
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
                // now that it's set let's update the mod
                ((TextField) scene.lookup("#" + subclass + "-" + field + "_" + elements[0] + "-" + "1")).setText(String.valueOf(characterSheet.getStats().getStat(Integer.parseInt(elements[0]), 1)));
                // now update the total
                ((TextField) scene.lookup("#" + subclass + "-" + field + "_" + elements[0] + "-" + "0")).setText(String.valueOf(characterSheet.getStats().getStat(Integer.parseInt(elements[0]), 0)));
                // now update saving throws
                updateSavingThrows();
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
     * @param event
     */
    public void dynamicSpellAdder(ActionEvent event) {

        /*
        //get the number of children in the box minus 1 since it already has a box in it
        int index = spellPaneLevel0.getChildren().size() - 1;

        System.out.println("The size of the VBox is: " + index);
        // add the HBox to the arraylist so we can keep track of it
        spellLevel0HBoxList.add(new HBox(new ComboBox(list)));
        // find the HBox in the arraylist then the ComboBox so we can set the combobox's values
        ((ComboBox) ((spellLevel0HBoxList.get(index).getChildren()).get(0))).setEditable(true);
        ((ComboBox) ((spellLevel0HBoxList.get(index).getChildren()).get(0))).setPrefWidth(770);
        ((ComboBox) ((spellLevel0HBoxList.get(index).getChildren()).get(0))).setMaxWidth(1.7976931348623157E308);
        // add the member of the arraylist to the GUI
        spellPaneLevel0.getChildren().add(spellLevel0HBoxList.get(index));
        // disable the previous box
        */

        /*
        ComboBox temp = new ComboBox(list);
        temp.setEditable(true);
        temp.setPrefWidth(770);
        temp.setMaxWidth(1.7976931348623157E308);
        */

        int rowindex = spellLevel1Grid.getRowCount();

        // add new items to list
        spellLevel1Grid.add(new TextField(), 0, rowindex, 1, 1);
        spellLevel1Grid.add(new ComboBox(list), 1, rowindex, 1, 1);
        spellLevel1Grid.add(new Button("X"), 2, rowindex, 1, 1);
       // spellLevel1Grid.getRowConstraints().add(rowindex, new RowConstraints(30));
                //set(rowindex, new RowConstraints(30));
                //add(new RowConstraints(30));


        // get all the fields we're currently working with to format them
        TextField currentfield = (TextField) getNodeFromGridPane(spellLevel1Grid, 0, rowindex);
        ComboBox currentbox = (ComboBox) getNodeFromGridPane(spellLevel1Grid, 1, rowindex);
        Button currentbutton = (Button) getNodeFromGridPane(spellLevel1Grid, 2, rowindex);

        //format the items
        currentbox.setEditable(true);
        currentbox.setPrefWidth(770);
        currentbox.setMaxWidth(1.7976931348623157E308);
        //spellLevel1Grid.setMargin(currentbox, new Insets(3, 0, 0, 0));

       currentbutton.setOnAction(new EventHandler<ActionEvent>() {
           @Override public void handle(ActionEvent e) {

              spellLevel1Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == rowindex);
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
}
