package DnDCharacterSheet;

/**
 *
 */
public class Story {

    private String personality;
    private String ideals;
    private String bonds;
    private String flaws;
    private String backstory;
    private String allies;
    private String otherProf;

    /**
     *
     * @param personality
     * @param ideals
     * @param bonds
     * @param flaws
     * @param backstory
     * @param allies
     * @param otherProf
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

    public Story() {
        this.personality = "";
        this.ideals = "";
        this.bonds = "";
        this.flaws = "";
        this.backstory = "";
        this.allies = "";
        this.otherProf = "";
    }

    /**
     *
     * @return
     */
    public String getPersonality() {
        return personality;
    }

    /**
     *
     * @return
     */
    public String getIdeals() {
        return ideals;
    }

    /**
     *
     * @return
     */
    public String getBonds() {
        return bonds;
    }

    /**
     *
     * @return
     */
    public String getFlaws() {
        return flaws;
    }

    /**
     *
     * @return
     */
    public String getBackstory() {
        return backstory;
    }

    /**
     *
     * @return
     */
    public String getAllies() {
        return allies;
    }

    /**
     *
     * @return
     */
    public String getOtherProf() {
        return otherProf;
    }

    /**
     *
     * @param personality
     */
    public void setPersonality(String personality) {
        this.personality = personality;
    }

    /**
     *
     * @param ideals
     */
    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    /**
     *
     * @param bonds
     */
    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    /**
     *
     * @param flaws
     */
    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    /**
     *
     * @param backstory
     */
    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    /**
     *
     * @param allies
     */
    public void setAllies(String allies) {
        this.allies = allies;
    }

    /**
     *
     * @param otherProf
     */
    public void setOtherProf(String otherProf) {
        this.otherProf = otherProf;
    }
}
