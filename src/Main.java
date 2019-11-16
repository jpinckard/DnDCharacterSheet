import java.sql.Connection;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        Item item;
        //SQLiteHandler.Setup();
        Connection connection = SQLiteHandler.Setup();
        ArrayList<Spell> spells = SQLiteHandler.LoadSpells(connection);

        // Print all spells
        for(int i = 0; i < spells.size(); i++){
            System.out.println(spells.get(i).getName());
        }

        // Add a new spell
        Spell zoneOfTruth = new Spell(
                "Zone of Truth",
                "Cast a sphere in which victims must speak the truth.",
                1,
                1,
                1,
                "School",
                "Duration",
                "Range",
                "Save",
                "Components");

        SQLiteHandler.AddSpell(connection, zoneOfTruth);
        System.out.println(zoneOfTruth.getName() + " has been added to the databases.");

        SQLiteHandler.Sort("spells", "id");

        // Print all spells
        for(int i = 0; i < spells.size(); i++){
            System.out.println(spells.get(i).getName());
        }
    }
}
