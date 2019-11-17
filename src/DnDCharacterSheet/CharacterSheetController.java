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
        characterSheet.setStory(new Story("", "", "", "", "", "", ""));
        characterSheet.setStats(new Stats());
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
        //  TEST!! DELETE THIS!!!
        DefaultCharacterSheet();

        // Used to reset the caret position after filter.
        int caretPos = ((TextInputControl)event.getSource()).getCaretPosition();
        String id = ((Control)event.getSource()).getId(); // Get the id of the control
        String text = ((TextInputControl)event.getSource()).getText(); // Get the value in the field
        String[] values = id.split("_", 3); // The field name and data type are separated by an underscore
        String field = values[0]; // The field is the first value,
        String subclass = field.split("-")[0];
        field = field.split("-")[1];

        // Get the value to assign
        // Remove all non-numbers..
        text = text.replaceAll("[^\\d]", "");

        // Make sure there is at least one number in the field
        if (text != "" & text != null) {

            int value = Integer.parseInt(text);

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

                Integer[] obj;

                // Set Value
                if (subclass!=null){
                    Field subclassField = (CharacterSheet.class.getDeclaredField(subclass));
                    subclassField.setAccessible(true);

                    // Make a copy of the target field within CharacterSheet
                    obj = (Integer[])targetField.get(characterSheet);
                    // Set the target field to hold our new value
                    obj[Integer.parseInt(elements[0])] = value;
                    // Set the target field to hold the copied object
                    targetField.set(subclassField.get(characterSheet), obj);
                    // Reset the subclass instantiation within character sheet
                    subclassField.set(characterSheet, obj);
                }
                else{
                    // Make a copy of the target field within CharacterSheet
                    obj = (Integer[])targetField.get(characterSheet);
                    // Set the target field to hold our new value
                    obj[Integer.parseInt(elements[0])] = value;
                    // Set the field to hold the copy object
                    targetField.set(characterSheet, obj);}
            }
        }

        // Assign value to original field.
        ((TextInputControl)event.getSource()).setText(text);

        // Reposition the caret
        ((TextInputControl)event.getSource()).positionCaret(caretPos);
    }
}
