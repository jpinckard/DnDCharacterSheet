package DnDCharacterSheet;

/**
 * Class that holds all story information of the character.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Story {

    private String personality; //string associated with the character's personality
    private String ideals; //string associated with the character's ideals
    private String bonds; //string associated with the character's bonds
    private String flaws; //string associated with the character's flaws
    private String backstory; //string associated with the character's backstory
    private String allies; //string associated with the character's allies
    private String otherProf; //string associated with the character's other proficiencies

    /**
     * Parameterized constructor for Story class.
     *
     * @param personality - string for personality
     * @param ideals - string for ideals
     * @param bonds - string for bonds
     * @param flaws - string for flaws
     * @param backstory - string for backstory
     * @param allies - string for allies
     * @param otherProf - string for other proficiencies
     */
    public Story(String personality, String ideals, String bonds, String flaws,
                 String backstory, String allies, String otherProf) {
        this.personality = personality;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;
        this.backstory = backstory;
        this.allies = allies;
        this.otherProf = otherProf;
    }

    /**
     * Default constructor for Story class.
     */
    public Story() {
        this.personality = "";
        this.ideals = "";
        this.bonds = "";
        this.flaws = "";
        this.backstory = "";
        this.allies = "";
        this.otherProf = "";
    }

    public String getPersonality() {
        return personality;
    }

    public String getIdeals() {
        return ideals;
    }

    public String getBonds() {
        return bonds;
    }

    public String getFlaws() {
        return flaws;
    }

    public String getBackstory() {
        return backstory;
    }

    public String getAllies() {
        return allies;
    }

    public String getOtherProf() {
        return otherProf;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public void setAllies(String allies) {
        this.allies = allies;
    }

    public void setOtherProf(String otherProf) {
        this.otherProf = otherProf;
    }
}
