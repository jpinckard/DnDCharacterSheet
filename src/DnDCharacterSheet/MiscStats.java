package DnDCharacterSheet;

public class MiscStats {

    private int profBonus;
    private int[] AC = new int[6];
    private int[] init = new int[3];
    private int[] speed = new int[6];
    private int carryLoad;
    private Stats stats;

    public MiscStats()
    {
        profBonus = 2;
        AC =  new int[]{ 0,0,0,0,0,0 };
        init = new int[]{ 0,0,0};
        speed = new int[]{30,30,0,15,0};
        carryLoad = 0;
    }

    public MiscStats(int profBonus, int[] AC, int[] init, int[] speed, int carryLoad) {
        this.profBonus = profBonus;
        this.AC = AC;
        this.init = init;
        this.speed = speed;
        this.carryLoad = carryLoad;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getProfBonus() {
        return profBonus;
    }

    public int[] getAC() {
        return AC;
    }

    public int[] getInit() {
        return init;
    }

    public int[] getSpeed() {
        return speed;
    }

    public int getCarryLoad() {
        return carryLoad;
    }

    public void setProfBonus(int profBonus) {
        this.profBonus = profBonus;
    }

    public void setAC(int[] AC) {
        this.AC = AC;
    }

    public void setInit(int[] init) {
        this.init = init;
    }

    public void setSpeed(int[] speed) {
        this.speed = speed;
    }

    public void setCarryLoad(int carryLoad) {
        this.carryLoad = carryLoad;
    }

    public void calcAC(Equipment equip)
    {
        AC[1] = equip.getArmor().getBaseAC();
        if(equip.isShield())
        {
            AC[2] = 2;
        }
        else
            AC[2] = 0;
        AC[3] = stats.getStat(1,1); //Dex Mod
        AC[5] = (10+stats.getStat(1,1));

        if(equip.getArmorType().equals("light"))
        {
            AC[0] = AC[1] + AC[2] + AC[3] + AC[4];
        }
        if(equip.getArmorType().equals("medium"))
        {
            if(AC[3] > 2)
            {
                AC[0] = AC[1] + AC[2] + 2 + AC[4];
            }
            else
                AC[0] = AC[1] + AC[2] + AC[3] + AC[4];
        }
        else
            AC[0] = AC[1] + AC[2] + AC[4];
    }

    public void calcInit()
    {
        init[1] = stats.getStat(1,1);
        init[0] = init[1] + init[2];
    }

    public void calcCarryLoad()
    {
        carryLoad = 10*stats.getStat(0,0);
    }
}
