package DnDCharacterSheet;

/**
 * The armor class stores the information required from an armor
 * item in order to determine its effectiveness. It extends the Item class
 * to take advantage of the basic functionality of anything that can be worn or carried in the game.
 */
public class Armor extends Item
{
    private int baseAC; // Armor provided by the armor before mods are taken into consideration
    private String aGroup; // String equal to "light", "medium", or "heavy". This describes the classification of the armor being used.
    private boolean stealthDisadvantage; // Some suits of armor pose a passive stealth disadvantage. If this field is true, the armor has this attribute

    /**
     * Sets all private variables to default values.
     */
    public Armor()
    {
        baseAC = 10;
        aGroup = "";
        stealthDisadvantage = false;
    }

    public Armor(String name, float weight, String category, String description, int amount, float cost, int baseAC, String aGroup, boolean stealthDisadvantage) {
        super(name, weight, category, description, amount, cost);
        this.baseAC = baseAC;
        this.aGroup = aGroup;
        this.stealthDisadvantage = stealthDisadvantage;
    }

    /**
     * Sets value for base AC.
     * @param baseAC value of baseAC
     */
    public void setBaseAC(int baseAC) {
        this.baseAC = baseAC;
    }

    /**
     * Sets armor group for later calculation.
     * @param aGroup name of armor group
     */
    public void setaGroup(String aGroup) {
        this.aGroup = aGroup;
    }

    /**
     * Sets stealthDisadvantage to true or false.
     * @param stealthDisadvantage determines weather or not the armor gives disadvantage on stealth
     */
    public void setStealthDisadvantage(boolean stealthDisadvantage) {
        this.stealthDisadvantage = stealthDisadvantage;
    }

    /**
     * Returns base armor value for armor.
     * @return baseAC
     */
    public int getBaseAC() {
        return baseAC;
    }

    /**
     * Returns group of armor
     * @return aGroup armor type
     */
    public String getaGroup()
    {
        return aGroup;
    }

    /**
     * Return if the armor gives disadvantage on stealth or not
     * @return stealthDisadvantage if the armor gives disadvantage on stealth or not.
     */
    public boolean isStealthDisadvantage() {
        return stealthDisadvantage;
    }


}
