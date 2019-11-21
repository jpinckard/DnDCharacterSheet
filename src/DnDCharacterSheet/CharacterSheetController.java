package DnDCharacterSheet;

import java.lang.reflect.Field;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CharacterSheetController {

    CharacterSheet characterSheet = new CharacterSheet();

    public void SetValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        /**************************
         **** DEFINE VARIABLES ****
        **************************/
        // Used to reset the caret position after filter.
        int caretPos = ((TextInputControl)event.getSource()).getCaretPosition();

        // Get the id of the control
        String id = ((Control)event.getSource()).getId();
        // Get the value in the field
        String text = ((TextInputControl)event.getSource()).getText();

        // The field name and data type are separated by an underscore
        String[] values = id.split("_", 3);
        // The field is the first value,
        String field = values[0];
        // And the datatype is the second.
        String dataType  = values[1];
        // Get the other instance of the field if it exists
        boolean instance = false;
        if (values.length > 2){ instance = !Boolean.parseBoolean(values[2]);}

        // Get the field we're editing
        Field targetField = null;
        Field subclassField = null;
        // Check to see if we're referencing a subclass
        // The field name and data type are separated by an underscore
        String[] subclasses = field.split("-", 2);

        /**************************
         *** GET SELECTED FIELD ***
         **************************/
        if (subclasses.length > 1){
            System.out.println("Set " + subclasses[0] + "." + subclasses[1] + ".");
            // The subclass is the first value,
            String subclass = subclasses[0];
            field = subclasses[1];
            // And its field is the second.
            subclassField = (CharacterSheet.class.getDeclaredField(subclass));
            targetField = (CharacterSheet.class.getDeclaredField(subclass)).getType().getDeclaredField(field);
        } else{
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
        ((TextInputControl)event.getSource()).setText(text);

        if (values.length > 2){
            // Set the other field. (Use !instance to find the other field.)
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + !instance )).setText(text);
        }

        // Reposition the caret
        ((TextInputControl)event.getSource()).positionCaret(caretPos);
    }

    public String Filter(Field subclass, Field field, String text, String dataType) throws IllegalAccessException, NoSuchFieldException {

        field.setAccessible(true);
        Object value = null;
        String element = "";
        // Get array element
        if (dataType.contains("array")){
            element = dataType.split("-")[1];
            dataType = dataType.split("-")[0];
        }

        System.out.println("Data type: " + dataType + "; fieldName: " + field.getName() );
        // Ensure text meets requirements
        switch (dataType) {
            case "int":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){value = Integer.parseInt(text);}
                break;
            case "float":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d.]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){value = Float.parseFloat(text);}
                break;
            case "str":
                value = text;
                break;
        }

        if (value != null){
            text = value.toString();
            // Set Value
            if (subclass!=null & value != null){
                // Make the subclass accessible if it's not null
                subclass.setAccessible(true);
                // Make a copy of the subclass variable within CharacterSheet
                System.out.println("Subclass: " + subclass.getName());
                Object obj = subclass.get(characterSheet);
                System.out.println("obj: " + obj.toString());
                // Change the subclass instantiation to have our new value
                field.set(obj, value);
                // Reset the subclass instantiation within character sheet
                subclass.set(characterSheet, obj);
            } else{
                field.set(characterSheet, value);
            }
        }

        return text;
    }

    public void SetArrayValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException {
        //subclass-field_element1-element2

        /**************************
         **** DEFINE VARIABLES ****
         **************************/
        // Used to reset the caret position after filter.
        int caretPos = ((TextInputControl)event.getSource()).getCaretPosition();
        String id = ((Control)event.getSource()).getId(); // Get the id of the control
        String text = ((TextInputControl)event.getSource()).getText(); // Get the value in the field
        String[] values = id.replace("array-","").split("_", 3); // The field name and data type are separated by an underscore
        String field = values[0]; // The field is the first value,
        String subclass = field.split("-")[0];
        field = field.split("-")[1];
        int value = 0;

        // Get the value to assign
        // Remove all non-numbers..
        text = text.replaceAll("[^\\d]", "");

        // Make sure there is at least one number in the field
        if (text != "" && text != null) {

            System.out.println("Value of text: " + text);

            if (!text.equals("")){value = Integer.parseInt(text);}

            // And the datatype is the second.
            String[] elements = values[1].split("-");
            // Set 2-Dimensional Array
            if (elements.length > 1){
                characterSheet.getStats().setStat(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), value);
            }
            // Set 1-Dimensional Array
            else{
                Field targetField = (CharacterSheet.class.getDeclaredField(subclass)).getType().getDeclaredField(field);
                targetField.setAccessible(true);

                // Set Value
                if (subclass!=null){
                    Field subclassField = (CharacterSheet.class.getDeclaredField(subclass));
                    subclassField.setAccessible(true);

                    // Get value of target field
                    int[] temp = (int[])targetField.get(subclassField.get(characterSheet));
                    // Change the value of the copy
                    temp[Integer.parseInt(elements[0])] = value;
                    // Set the target field to hold the copied object
                    targetField.set(subclassField.get(characterSheet), temp);
                }
                else{
                    // Make a copy of the target field within CharacterSheet
                    int[] temp = (int[])targetField.get(characterSheet);
                    // Set the target field to hold our new value
                    temp[Integer.parseInt(elements[0])] = value;
                    // Set the field to hold the copy object
                    targetField.set(characterSheet, temp);
                }
            }
        }

        System.out.println("Misc stats AC value: " + characterSheet.getMiscStats().getAC()[3]);

        // Assign value to original field.
        ((TextInputControl)event.getSource()).setText(text);

        // Reposition the caret
        ((TextInputControl)event.getSource()).positionCaret(caretPos);
    }

    //Define a method that calls setarrayvalue and character stats calculations for the character stats
    public void setMainStats (KeyEvent event) throws NoSuchFieldException, IllegalAccessException {

        //declare a temporary 2D array to make the IDE happy
        /******DELETE LATER*******/
        int tempGrid[][] = new int[6][6];

        //use reflection to get the ID of the box so that we can update every box: stats-statsGrid_i-j
        //where i is the ROW of the stat and j is the boxes 0 and 1

        Scene scene = ((Control) event.getSource()).getScene(); //get the scene to set the boxes
        String id = ((Control)event.getSource()).getId(); // Get the id of the control
        String text = ((TextInputControl)event.getSource()).getText(); // Get the value in the field
        String[] values = id.replace("array-","").split("_", 3); // The field name and data type are separated by an underscore
        String field = values[0]; // The field is the first value,
        String subclass = field.split("-")[0];
        field = field.split("-")[1];

        //call the setArrayValue function to update the model
        SetArrayValue(event);

        //call the method that calculates the mod and total and stick it here
        //method here


       // Set the total field.
       // ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + !instance )).setText(text);





    }

}
