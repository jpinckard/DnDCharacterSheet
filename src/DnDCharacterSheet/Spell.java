package DnDCharacterSheet;

public class Spell {
    private String name;
    private String description;
    private int level;
    private int prep;
    private int used;
    private String school;
    private String duration;
    private String range;
    private String save;
    private String components;

    public Spell(String name, String description, int level, int prep, int used, String school, String duration, String range, String save, String components) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.prep = prep;
        this.used = used;
        this.school = school;
        this.duration = duration;
        this.range = range;
        this.save = save;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrep() {
        return prep;
    }

    public void setPrep(int prep) {
        this.prep = prep;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
