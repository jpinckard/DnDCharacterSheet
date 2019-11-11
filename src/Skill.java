public class Skill {
    private String name;
    private int modType;
    private boolean prof;
    private boolean expert;
    private int misc;


    public String getName() {
        return name;
    }

    public int getModType() {
        return modType;
    }

    public boolean isProf() {
        return prof;
    }

    public void setProf(boolean prof) {
        this.prof = prof;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }
}
