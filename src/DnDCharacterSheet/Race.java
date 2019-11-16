package DnDCharacterSheet;

/**
 *A class for producing the race type of the character. There is a stat boost for each stat associated with the class,
 * as well as a speed.
 */
public class Race {

    private String rName;//string associated with the race name
    private int[] statBoost;//an array for the stat boosts
    private int speed;//int associated with the speed

    /**
     *Constructs a Race object with base values.
     */
    public Race()
    {
        rName = "";
        statBoost = new int[6];
    }

    /**
     *Constructs a Race object based on the parameters.
     * @param rName - string associated with the race name
     */
    public Race(String rName) {
        this.rName = rName;
        statBoost = new int[6];
    }

    /**
     *Returns the array of stat boosts.
     * @return - array of ints for the stat boosts
     */
    public int[] getStatBoost()
    {
        return statBoost;
    }

    /**
     *Sets the array of stat boosts
     * @param statBoost - array of ints for the stat boosts
     */
    public void setStatBoost(int[] statBoost)
    {
        this.statBoost = statBoost;
    }

    /**
     *Returns the race name.
     * @return - string associated to the race name
     */
    public String getrName() {
        return rName;
    }

    /**
     *Sets the race name based on the parameter.
     * @param rName - string for the race name
     */
    public void setrName(String rName) {
        this.rName = rName;
    }

    /**
     *Returns the speed.
     * @return - int associated to the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *Sets the speed based on the parameter.
     * @param speed - int associated to the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
