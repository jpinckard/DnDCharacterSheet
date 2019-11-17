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
    }

    public void SetValue(KeyEvent event) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        
        //  TEST!! DELETE THIS!!!
        DefaultCharacterSheet();

        // Used to reset the caret position after filter.
        int caretPos = ((TextField)event.getSource()).getCaretPosition();

        // Get the id of the control
        String id = ((TextField)event.getSource()).getId();
        // Get the value in the field
        String text = ((TextField)event.getSource()).getText();

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

        if (subclasses.length > 1){
            System.out.println(subclasses[0] + " and " + subclasses[1]);
            // The subclass is the first value,
            String subclass = subclasses[0];
            field = subclasses[1];
            // And its field is the second.
            // Charactersheet.field.field; example being charactersheet.info.background
            System.out.println("Class: " + (CharacterSheet.class.getDeclaredField(subclass)).getType().toString());
            myClass = (CharacterSheet.class.getDeclaredField(subclass)).getType();
            myClassField = (CharacterSheet.class.getDeclaredField(subclass));
            myField = myClass.getDeclaredField(field);
        } else{
            // Get the field we're editing
            myField = CharacterSheet.class.getDeclaredField(field);
        }


        // Filter text values
        text = Filter(myClassField, myField, text, dataType);

        // Make sure all instances of the control are identical
        // Get the current scene.
        Scene scene = ((TextField) event.getSource()).getScene();
        // Get the other instance of this control if they exist.
        if (values.length > 2){
            // Set the value of the field.
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + instance )).setText(text);
            // Set the other field. (Use !instance to find the other field.)
            ((TextField) scene.lookup("#" + values[0] + "_" + dataType + "_" + !instance )).setText(text);
        }
        else{
            // Assign value to field with no clones.
            ((TextField) scene.lookup("#" + id)).setText(text);
        }


        // Reposition the caret
        ((TextField)event.getSource()).positionCaret(caretPos);

        System.out.println(field + " is set to " + text + ".");
    }

    public String Filter(Field subclass, Field field, String text, String dataType) throws IllegalAccessException, NoSuchFieldException {

        field.setAccessible(true);
        Object value = null;

        // Ensure text meets requirements
        switch (dataType) {
            case "int":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){
                    // Parse the value as an integer.
                    value = Integer.parseInt(text);
                }
                break;
            case "float":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d.]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){
                    // Parse the value as an integer.
                    value = Float.parseFloat(text);
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
