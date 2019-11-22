package DnDCharacterSheet;

/**
 * The HitPoints class determines the current physical wellbeing of the character. The amount of hit points (HP)
 * the character has determines how much damage the character can take before they fall unconscious. While unconscious
 * the character has to roll death saving throws to determine if they die or not. At 3 positive saves (rolls of 11-20)
 * the character stabilizes. At 3 negative saves (rolls of 1-10) the character is dead.
 */
public class HitPoints
{
    private int currentHP; // Current number of hit points of a character.
    private int maxHP; // Maximum amount of HP the character can have.
    private int tempHP; // Amount of temporary HP a character has (magical shielding or similar effects providing HP).
    private int bleedOut; // store the value when you fall unconscious
    private int nonLethal; // store the value of nonlethal damage
    private int positiveSave; // returns number of positive saves.
    private int negativeSave; // returns number of negative saves.
    private String conditions;

    /**
     * Default constructor that sets all values to 0
     */
    public HitPoints()
    {
        currentHP = 0;
        maxHP = 0;
        tempHP = 0;
        bleedOut = 0;
        nonLethal = 0;
        positiveSave = 0;
        negativeSave = 0;
    }

    /**
     * Sets the currentHP of the character.
     * @param currentHP of the character
     */
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * Sets the maximum HP of the character.
     * @param maxHP of the character
     */
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * Sets the amount of negative saves the character has.
     * @param negativeSave number of negative saves
     */
    public void setNegativeSave(int negativeSave) {
        this.negativeSave = negativeSave;
    }

    /**
     * Sets the amount of positive saves the character has.
     * @param positiveSave number of positive saves
     */
    public void setPositiveSave(int positiveSave) {
        this.positiveSave = positiveSave;
    }

    /**
     * Returns the amount of temporary HP the character has.
     * @param tempHP number of temporary terms
     */
    public void setTempHP(int tempHP) {
        this.tempHP = tempHP;
    }

    /**
     * Returns the current HP of the character.
     * @return current HP of character
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Returns the characters maximum HP.
     * @return max HP of the character
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the number of negatives saves incurred so far.
     * @return the number of negative saves
     */
    public int getNegativeSave() {
        return negativeSave;
    }

    /**
     * Returns the number of positives saves incurred so far.
     * @return the number of positives saves
     */
    public int getPositiveSave() {
        return positiveSave;
    }

    /**
     * Returns the amount of temporary HP of the character.
     * @return Returns the temporary HP of the character
     */
    public int getTempHP() {
        return tempHP;
    }

    /**
     * returns the amount of total HP (current plus temporary)
     * @return the total amount of health of the character
     */
    public int getTotalHP() {
        return currentHP + tempHP;
    }

    public int getBleedOut(){ return bleedOut; }

    public void changeCurrentHP(int amount){
        if (amount < 0){
            dealDamage(Math.abs(amount));
        System.out.println("Dealing damage. Current HP: " + currentHP);
        } else{
            heal(amount);
            System.out.println("Healing. Current HP: " + currentHP);
        }
    }

    /**
     * Subtracts amount of damage dealt from totalHP. Does go negative for possible instant kill effects.
     * @param damage damage dealt to the character
     */
    public void dealDamage(int damage)
    {
        int pain = 0;
        pain = tempHP - damage;
        if(pain <= 0)
        {
            currentHP = currentHP + pain;
            tempHP = 0;
        }
        else
        {
            tempHP = tempHP - damage;
        }
    }

    /**
     * Adds health to the character if they are healed
     * @param health value of healing given to character.
     */
    public void heal(int health)
    {
        if((currentHP + health) >= maxHP)
        {
            currentHP = maxHP;
        }
        else
        {
            currentHP = currentHP + health;
        }
    }

    /**
     * Adds temporary hit points to the character
     * @param tHP amount of temporary hitpoints added to the character
     */
    public void addTempHP(int tHP)
    {
        tempHP = tempHP + tHP;
    }

    /**
     * Calculates amount of HP a character can get based on class (profession), stats, and level.
     * @param profession the hit die comes from this class (base value used when calculating HP) number passed in is type of die that would be rolled.
     * @param stats The constitution modifier from the stats object is added to the die roll to find the hit points gained at each level.
     * @param level The level is used to determine the amount of rolls the character gets.
     */
    public void calculateMaxHP(Profession profession, Stats stats, int level)
    {
        int tempMaxHP = (int) ((profession.getHd()/2 + 1 + stats.getStat(3,1))*(level-1));
        tempMaxHP = tempMaxHP + profession.getHd()+stats.getStat(3,1); // First level of HP uses max of die roll
        maxHP = tempMaxHP;
    }

    /**
     * Calculates the difference between the value you fall unconscious at and your max HP
     * This is used to determine the value of the progressbar representing your health
     * @param maxHP
     * @param bleedOut
     * @return
     */

    public int calculateHpDiff(int maxHP, int bleedOut){
        int hpDiff = maxHP + bleedOut;
        return hpDiff;
    }
}







