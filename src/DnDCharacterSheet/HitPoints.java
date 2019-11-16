package DnDCharacterSheet;

public class HitPoints
{
    private int currentHP;
    private int maxHP;
    private int tempHP;
    private int positiveSave; // returns number of positive saves
    private int negativeSave; // returns number of negative saves

    public HitPoints()
    {
        currentHP = 0;
        maxHP = 0;
        tempHP = 0;
        positiveSave = 0;
        negativeSave = 0;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setNegativeSave(int negativeSave) {
        this.negativeSave = negativeSave;
    }

    public void setPositiveSave(int positiveSave) {
        this.positiveSave = positiveSave;
    }

    public void setTempHP(int tempHP) {
        this.tempHP = tempHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getNegativeSave() {
        return negativeSave;
    }

    public int getPositiveSave() {
        return positiveSave;
    }

    public int getTempHP() {
        return tempHP;
    }

    public int getTotalHP() {
        return currentHP + tempHP;
    }

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

    public void addTempHP(int tHP)
    {
        tempHP = tempHP + tHP;
    }


}







