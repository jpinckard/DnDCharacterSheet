package DnDCharacterSheet;

/**
 *A class for producing the race type of the character. There is a stat boost for each stat associated with the class,
 * as well as a speed.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Race {

    private String rName;//string associated with the race name
    private int[] statBoost;//an array for the stat boosts
    private int speed;//int associated with the speed

    /**
     *Default constructor for the Race class.
     */
    public Race()
    {
        rName = "";
        statBoost = new int[6];
    }

    /**
     * Parameterized constructor for the Race class.
     *
     * @param rName - string for the race name
     * @param statBoost - array for stat boosts
     * @param speed - int for speed
     */
    public Race(String rName, int[] statBoost, int speed)
    {
        this.rName = rName;
        this.statBoost = statBoost;
        this.speed = speed;
    }

    /**
     * Parameterized constructor for the Race class.
     *
     * @param rName - string for the race name
     */
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
