package DnDCharacterSheet;

public class Skill {
    private String name;
    private int modType;
    private boolean trained;
    private int misc;
    private int prof;
    private int expert;

    public Skill(String name, int modType, boolean trained, int prof, int expert, int misc){
        this.name = name;
        this.modType = modType;
        this.trained = trained;
        this.prof = prof;
        this.expert = expert;
        this.misc = misc;
    }

    public String getName() {
        return name;
    }

    public int getModType() {
        return modType;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public boolean getTrained() {return trained;}

    public int getExpert() {
        return expert;
    }

    public void setExpert(int expert) {
        this.expert = expert;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }

    public int getProf() {return prof;}

    public void setProf(int prof) {this.prof = prof;}
}
