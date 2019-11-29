package DnDCharacterSheet;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * A ListCell Class to format the comboboxes on the spell sheet.
 */

public class SpellCell extends ListCell<Spell> {

    /**
     * Item update function to specify how items in the observablelist are displayed.
     * @param spell
     * @param empty
     */
    @Override
    protected void updateItem(Spell spell, boolean empty){

        super.updateItem(spell, empty);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        final GridPane cellGrid = new GridPane();
        Label lblLevel = new Label();
        Label lblName = new Label();
        Label lblSchool = new Label();
        Label lblDuration = new Label();
        Label lblRange = new Label();
        Label lblSave = new Label();

            cellGrid.getColumnConstraints().addAll(
                    new ColumnConstraints(50, 50, -1),
                    new ColumnConstraints(10, 400, -1),
                    new ColumnConstraints(50, 50, -1),
                    new ColumnConstraints(50, 50, -1),
                    new ColumnConstraints(50, 50, -1),
                    new ColumnConstraints(50, 50, -1),
                    new ColumnConstraints(30, 29, 20)
            );

            cellGrid.add(lblLevel, 0, 1);
            cellGrid.add(lblName, 1, 1);
            cellGrid.add(lblSchool, 2, 1);
            cellGrid.add(lblDuration, 3, 1);
            cellGrid.add(lblRange, 4, 1);
            cellGrid.add(lblSave, 5, 1);


        if(empty || spell == null){
            setGraphic(null);
            setText(null);
        } else{

            lblLevel.setText(String.valueOf(spell.getLevel()));
            lblLevel.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");
            lblName.setText(spell.getName());
            lblName.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");
            lblSchool.setText(spell.getSchool());
            lblSchool.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");
            lblDuration.setText(spell.getDuration());
            lblDuration.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");
            lblRange.setText(spell.getRange());
            lblRange.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");
            lblSave.setText(spell.getSave());
            lblSave.setStyle("-fx-opacity: 1; -fx-text-fill: #000000");

            setGraphic(cellGrid);
        }
    }

}
