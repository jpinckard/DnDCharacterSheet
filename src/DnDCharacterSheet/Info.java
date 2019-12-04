package DnDCharacterSheet;

import java.lang.*;

/**
 * The info class holds all the basic character info.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Info {

    private String name; //string associated with the character's name
    private String ht; //string associated with the character's height
    private String eyes; //string associated with the character's eye color
    private String skin; //string associated with the character's skin color
    private String hair; //string associated with the character's hair color
    private String gender; //string associated with the character's gender
    private String deity; //string associated with the character's deity
    private String homeland; //string associated with the character's homeland
    private String languages; //string associated with the character's languages known
    private String alignment; //string associated with the character's alignment
    private String size; //string associated with the character's size
    private String background; //string associated with the character's background
    private String misc;
    private int age; //int associated with the character's age
    private int wt; //int associated with the character's weight in pounds

    //This is a temporary holder for features until a future version
    private String features;

    /**
     * Parameterized constructor for class Info.
     * @param name - string for the name
     * @param ht - string for the height
     * @param eyes - string for eye color
     * @param skin - string for skin color
     * @param hair - string for hair color
     * @param gender -string for gender
     * @param deity - string for deity
     * @param homeland - string for homeland
     * @param languages - string for languages
     * @param alignment - string for alignment
     * @param size - string for size
     * @param background - string for background
     * @param age - int for age
     * @param wt - int for weight
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
