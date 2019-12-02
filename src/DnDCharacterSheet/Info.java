package DnDCharacterSheet;

import java.lang.*;

/**
 * Class that holds all the basic character info.
 */
public class Info {

    private String name;
    private String ht;
    private String eyes;
    private String skin;
    private String hair;
    private String gender;
    private String deity;
    private String homeland;
    private String languages;
    private String alignment;
    private String size;
    private String background;
    private String misc;
    private int age;
    private int wt;

    //This is a temporary holder for features until a future version
    private String features;

    /**
     * Parameterized constructor for class Info.
     * @param name
     * @param ht
     * @param eyes
     * @param skin
     * @param hair
     * @param gender
     * @param deity
     * @param homeland
     * @param languages
     * @param alignment
     * @param size
     * @param background
     * @param age
     * @param wt
     */
    public Info(String name, String ht, String eyes, String skin, String hair,
                String gender, String deity, String homeland, String languages,
                String alignment, String size, String background, String misc, int age, int wt, String features) {
        this.name = name;
        this.ht = ht;
        this.eyes = eyes;
        this.skin = skin;
        this.hair = hair;
        this.gender = gender;
        this.deity = deity;
        this.homeland = homeland;
        this.languages = languages;
        this.alignment = alignment;
        this.size = size;
        this.background = background;
        this.age = age;
        this.wt = wt;
        this.misc = misc;
        this.features = features;
    }

    /**
     * Default constructor for class Info.
     */
    public Info() {
        this.name = "";
        this.ht = "";
        this.eyes = "";
        this.skin = "";
        this.hair = "";
        this.gender = "";
        this.deity = "";
        this.homeland = "";
        this.languages = "";
        this.alignment = "";
        this.size = "";
        this.background = "";
        this.misc = "";
        this.age = 0;
        this.wt = 0;
        this.features = "";
    }

    public String getName() {
        return name;
    }

    public String getHt() {
        return ht;
    }

    public String getEyes() {
        return eyes;
    }

    public String getSkin() {
        return skin;
    }

    public String getHair() {
        return hair;
    }

    public String getGender() {
        return gender;
    }

    public String getDeity() {
        return deity;
    }

    public String getHomeland() {
        return homeland;
    }

    public String getLanguages() {
        return languages;
    }

    public String getAlignment() {
        return alignment;
    }

    public String getSize() {
        return size;
    }

    public String getBackground() {
        return background;
    }

    public int getAge() {
        return age;
    }

    public int getWt() {
        return wt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDeity(String deity) {
        this.deity = deity;
    }

    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }
}
