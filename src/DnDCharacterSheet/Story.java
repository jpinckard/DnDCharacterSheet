package DnDCharacterSheet;

public class Story {

    private String personality;
    private String ideals;
    private String bonds;
    private String flaws;
    private String backstory;
    private String allies;
    private String otherProf;

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
