package DnDCharacterSheet;

public class Armor extends Item
{
    private int baseAC;
    private String aGroup;
    private boolean stealthDisadvantage;


    public Armor()
    {
        baseAC = 10;
        aGroup = "";
        stealthDisadvantage = false;
    }


    public void setBaseAC(int baseAC) {
        this.baseAC = baseAC;
    }

    public void setaGroup(String aGroup) {
        this.aGroup = aGroup;
    }

    public void setStealthDisadvantage(boolean stealthDisadvantage) {
        this.stealthDisadvantage = stealthDisadvantage;
    }

    public int getBaseAC() {
        return baseAC;
    }

    public String getaGroup() {
        return aGroup;
    }

    public boolean isStealthDisadvantage() {
        return stealthDisadvantage;
    }


}
