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

    public void DefaultCharacterSheet(){
        characterSheet = new CharacterSheet();
        characterSheet.setInfo(new Info("", "", "", "", "", "", "",
                "", "", "", "", "", 0, 10));
        characterSheet.setMiscStats(new MiscStats());
    }



    public void SetValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        /**************************
         **** DEFINE VARIABLES ****
        **************************/
        //  TEST!! DELETE THIS!!!
        DefaultCharacterSheet();

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
        Field myField = null;
        Field myClassField = null;
        Class myClass = null;
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
            myClass = (CharacterSheet.class.getDeclaredField(subclass)).getType();
            myClassField = (CharacterSheet.class.getDeclaredField(subclass));
            myField = myClass.getDeclaredField(field);
        } else{
            // Get the field we're editing
            myField = CharacterSheet.class.getDeclaredField(field);
        }


        // Filter text values
        text = Filter(myClassField, myField, text, dataType);

        /**************************
         *** SET ALL CONTROL VALS *
         **************************/        // Get the current scene.
        Scene scene = ((Control) event.getSource()).getScene();
        // Get the other instance of this control if they exist.
        if (values.length > 2){
            // Set the value of the field.
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + instance )).setText(text);
            // Set the other field. (Use !instance to find the other field.)
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + !instance )).setText(text);
        }
        // Assign value to field with no clones.
        ((TextInputControl)event.getSource()).setText(text);
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
            case "array":
                System.out.println("Set Array");
                // Remove all non-numbers.
                text = text.replaceAll("[^\\d]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){ value = Integer.parseInt(text);}

                // Instantiate a new integer array.
                Integer[] array;

                // Store the array value.
                if (value != null) {
                    text = value.toString();
                    // Set Value
                    if (subclass != null & value != null) {
                        // Make the subclass accessible if it's not null
                        subclass.setAccessible(true);
                        // Make a copy of the subclass variable within CharacterSheet
                        Object obj = subclass.get(characterSheet);
                        // Create a copy of the target array.
                        array = (Integer[])field.get(obj);
                        // Change the relevant value.
                        array[Integer.parseInt(element)] = (Integer)value;
                        // Store the copy in character sheet
                        field.set(obj, array);
                        subclass.set(characterSheet, obj);
                    } else {
                        // Create a copy of the target array.
                        array = (Integer[])field.get(characterSheet);
                        // Change the relevant value.
                        array[Integer.parseInt(element)] = (Integer)value;
                        // Store the copy in character sheet
                        field.set(characterSheet, array);
                    }
                    value = null;
                }
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
                Object obj = subclass.get(characterSheet);
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
}
