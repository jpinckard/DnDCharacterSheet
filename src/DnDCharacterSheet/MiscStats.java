package DnDCharacterSheet;

/**
 *A class for calculating and producing other miscellaneous stats for a character.
 */
public class MiscStats {

    private int profBonus;//int associated with the proficiency bonus
    private int[] AC = new int[6];// array of ints for armor class
    private int[] init = new int[3];//array of ints for initiative
    private int[] speed = new int[6];//array of ints for speed
    private int carryLoad;//int associated with the carry load
    private Stats stats;//object of Stats to calculate misc stats

    /**
     *Constructs a MiscStats object with base values.
     */
    public MiscStats()
    {
        profBonus = 2;
        AC =  new int[]{ 0,0,0,0,0,0 };
        init = new int[]{ 0,0,0};
        speed = new int[]{30,30,0,15,0};
        carryLoad = 0;
    }

    /**
     *Constructs a MiscStats object based on the parameters.
     * @param profBonus - the number for the proficiency bonus
     * @param AC - an array for the armor class numbers for each stat
     * @param init - an array used to calculate the initiative number
     * @param speed - an array for determining the speed of the character
     * @param carryLoad - the number for the weight that the character can carry
     */
    public MiscStats(int profBonus, int[] AC, int[] init, int[] speed, int carryLoad) {
        this.profBonus = profBonus;
        this.AC = AC;
        this.init = init;
        this.speed = speed;
        this.carryLoad = carryLoad;
    }

    /**
     *Sets the stats based on the parameter passed in.
     * @param stats - an object of Stats type for the stats of the character
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     *Returns the number for the proficiency bonus.
     * @return the number for the proficiency bonus
     */
    public int getProfBonus() {
        return profBonus;
    }

    /**
     *Returns the array for armor class.
     * @return - array of ints for the armor class
     */
    public int[] getAC() {
        return AC;
    }

    /**
     *
     * @return
     */
    public int[] getInit() {
        return init;
    }

    /**
     *
     * @return
     */
    public int[] getSpeed() {
        return speed;
    }

    /**
     *
     * @return
     */
    public int getCarryLoad() {
        return carryLoad;
    }

    /**
     *
     * @param profBonus
     */
    public void setProfBonus(int profBonus) {
        this.profBonus = profBonus;
    }

    /**
     *
     * @param AC
     */
    public void setAC(int[] AC) {
        this.AC = AC;
    }

    /**
     *
     * @param init
     */
    public void setInit(int[] init) {
        this.init = init;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int[] speed) {
        this.speed = speed;
    }

    /**
     *
     * @param carryLoad
     */
    public void setCarryLoad(int carryLoad) {
        this.carryLoad = carryLoad;
    }

    /**
     *
     * @param equip
     */
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

        if(equip.getArmorType().equals("Light"))
        {
            AC[0] = AC[1] + AC[2] + AC[3] + AC[4];
        }
        if(equip.getArmorType().equals("Medium"))
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

    /**
     *
     */
    public void calcInit()
    {
        init[1] = stats.getStat(1,1);
        init[0] = init[1] + init[2];
    }

    /**
     *
     */
    public void calcCarryLoad()
    {
        carryLoad = 10*stats.getStat(0,0);
    }

    /**
     *Used to calculate the speed of a character based on its race.
     * @param race - the race of the character
     */
    public void calcSpeed(Race race)
    {
        speed[1] = race.getSpeed();
        speed[2] = 0;
        speed[3] = (int) speed[1]/2;
        speed[4] = (int) speed[1]/2;
    }

}
