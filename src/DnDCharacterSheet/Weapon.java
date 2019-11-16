package DnDCharacterSheet;

/**
 * The Weapon class stores the attributes of a weapon that distinguish it from an item
 * These values will be displayed to the user to let the user know how to use the weapon.
 */
public class Weapon extends Item
{
    private String damage; // Describes the damage of the weapon Ex. "1d6" means that a 6 sided die is rolled for damage
    private int range; // Describes the range in feet that the weapon can be used at. 5 for melee weapons unless special
    private boolean martial; //A martial weapon is more advanced and requires proficiency to be used. 0 for simple, 1 for martial
    private boolean ranged; //Described weather a weapon is ranged or melee. 0 for melee, 1 for ranged
    private String baseMod; //Specify if the stat used for attack damage is strength or dexterity based.

    /**
     * Instantiates the weapon class and sets all default values to the base for a very simple weapon.
     */
    public Weapon()
    {
        damage = "1d4";
        range = 0;
        martial = false;
        ranged = false;
        baseMod = "Strength";
    }

    /**
     * Sets the weapon damage.
     * @param damage is the damage caused by the weapon
     */
    public void setDamage(String damage) {
        this.damage = damage;
    }

    /**
     * Sets the type of baseMod.
     * @param baseMod the modifier used by the weapon
     */
    public void setBaseMod(String baseMod) {
        this.baseMod = baseMod;
    }

    /**
     * Sets the weapons status as martial or simple.
     * @param martial status as martial or simple
     */
    public void setMartial(boolean martial) {
        this.martial = martial;
    }

    /**
     * Sets the range of the weapon.
     * @param range range of the weapon
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Sets status as ranged or not on a weapon.
     * @param ranged status as ranged or not
     */
    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    /**
     * Returns damage dealt by weapon.
     * @return damage done by weapon
     */
    public String getDamage()
    {
        return damage;
    }

    /**
     * Returns range of the weapon in feet.
     * @return range of the wepaon in feet
     */
    public int getRange() {
        return range;
    }

    /**
     * Returns if the base mod is strength or dexterity for the weapon
     * @return strength or dexterity
     */
    public String getBaseMod() {
        return baseMod;
    }

    /**
     * Returns weapon status as a martial weapon or not.
     * @return true if weapon is martial
     */
    public boolean isMartial() {
        return martial;
    }

    /**
     * Returns status as ranged weapon or not.
     * @return true if weapon is ranged
     */
    public boolean isRanged() {
        return ranged;
    }
}








