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


        int caretPos = ((TextField)event.getSource()).getCaretPosition();

        // Get the id of the control
        String id = ((TextField)event.getSource()).getId();
        // Get the value in the field
        String text = ((TextField)event.getSource()).getText();

        // The field name and data type are separated by an underscore
        String[] values = id.split("_", 3);

        // Get class
        //Class<?> c = Class.forName(values[0]);

        // The field is the first value,
        String field = values[0];
        // And the datatype is the second.
        String dataType  = values[1];
        // Get the other instance of the field if it exists
        boolean instance = false;
        if (values.length > 2){ instance = !Boolean.parseBoolean(values[2]);}

        // Get the field we're editing
        Field privateStringField = CharacterSheet.class.getDeclaredField(field);

        // Filter text values
        text = Filter(privateStringField, text, dataType);

        // Make sure all instances of the control are identical
        // Get the current scene.
        Scene scene = ((TextField) event.getSource()).getScene();
        // Get the other instance of this control if they exist.
        if (values.length > 2){
            ((TextField) scene.lookup("#" + field + "_" + dataType + "_" + instance )).setText(text);

            ((TextField) scene.lookup("#" + field + "_" + dataType + "_" + !instance )).setText(text);
        }
        else{
            ((TextField) scene.lookup("#" + field + "_" + dataType )).setText(text);
        }

        // Reposition the caret
        ((TextField)event.getSource()).positionCaret(caretPos);

        System.out.println(field + " is set to " + text + ".");
    }

    public String Filter(Field field, String text, String dataType) throws IllegalAccessException {

        field.setAccessible(true);

        // Ensure text meets requirements
        switch (dataType) {
            case "int":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){
                    // Parse the value as an integer.
                    int value = Integer.parseInt(text);
                    // Set the field's final value
                    field.set(characterSheet, value);
                }
                break;
            case "float":
                // Remove all non-numbers..
                text = text.replaceAll("[^\\d.]", "");
                // Make sure there is at least one number in the field
                if (!text.equals("")){
                    // Parse the value as an integer.
                    float value = Float.parseFloat(text);
                    // Set the field's final value
                    field.set(characterSheet, value);

                }
                break;
            case "str":
                field.set(characterSheet, text);
                break;
        }
        return text;

    }
}
