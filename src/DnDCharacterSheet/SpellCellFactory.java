package DnDCharacterSheet;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Simple class to specify how the cellfactory in the comboboxes on the
 * spell sheet should display their cells.
 */
public class SpellCellFactory implements Callback<ListView<Spell>, ListCell<Spell>> {
    /**
     * Call method from implementing Callback that returns the actual SpellCell
     * formatter.
     * @param spellListView
     * @return
     */
    @Override
    public ListCell<Spell> call(ListView<Spell> spellListView) {
        return new SpellCell();
    }
}
