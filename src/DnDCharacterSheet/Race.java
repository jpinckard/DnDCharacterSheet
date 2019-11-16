package DnDCharacterSheet;

public class Race {

    private String rName;
    private int[] statBoost;

    public Race()
    {
        rName = "";
        statBoost = new int[6];
    }

    public Race(String rName) {
        this.rName = rName;
        statBoost = new int[6];
    }

    public int[] getStatBoost()
    {
        return statBoost;
    }

    public void setStatBoost(int[] statBoost)
    {
        this.statBoost = statBoost;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}
