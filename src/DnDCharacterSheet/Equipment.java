package DnDCharacterSheet;

import java.util.ArrayList;

/**
 * This class contains information on all of the equipment currently being used by the character
 */
public class Equipment
{
    private Armor armor; // Armor object for armor being worn
    private ArrayList<Weapon> weaponList; // List of readily available weapons

    private ArrayList<Armor> armorList; // List of armor in storage.
    private ArrayList<Item> ammoList; // List of ammunition available.
    private boolean shield; //True if character has a shield equipped, false if they do not

    /**
     * Default constructor for Equipment. Sets values to null while it waits for assignment.
     */
    public Equipment()
    {
        armor = null;
        weaponList = new ArrayList<Weapon>();
        shield = false;
    }

    public Equipment(Armor armor, ArrayList<Weapon> weaponList, ArrayList<Item> ammoList, boolean shield)
    {
        this.armor = armor;
        this.weaponList = weaponList;
        this.ammoList = ammoList;
        this.shield = shield;
    }

    /**
     * Returns the weight in lbs of all of the items the character has equipped.
     * @return float weight in lbs
     */
    public float getWeight()
    {
        float weight = armor.getWeight();
        for(Weapon x : weaponList)
        {
            weight = weight + x.getWeight();
        }
        for(Item x : ammoList)
        {
            weight = weight + x.getWeight();
        }

        return weight;
    }

    /**
     * Returns the type of armor worn by the character
     * @return String value for armor type
     */
    public String getArmorType()
    {
        return armor.getaGroup();
    }

    /**
     * Sets the armor object currently equipped.
     * @param armor object currently equipped
     */
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * Sets shield to true if shield is equipped, false otherwise.
     * @param shield determination if shield is equipped or not.
     */
    public void setShield(boolean shield) {
        this.shield = shield;
    }

    /**
     * Inputs list of weapons equipped
     * @param weaponList List of weapons to be equipped by the character
     */
    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    /**
     * Returns the list of armor in the inventory.
     * @return
     */
    public ArrayList<Armor> getArmorList() {
        return armorList;
    }

    /**
     * Sets the list of armor in the inventory
     * @param armorList
     */
    public void setArmorList(ArrayList<Armor> armorList) {
        this.armorList = armorList;
    }

    /**
     * Returns armor object currently equipped.
     * @return armor currently equipped
     */
    public Armor getArmor() {
        return armor;
    }

    /**
     * Returns list of weapons currently equipped
     * @return list of weapons equipped by character
     */
    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    /**
     * Returns true if a shield is equipped.
     * @return true if a shield is equipped
     */
    public boolean isShield() {
        return shield;
    }
}
