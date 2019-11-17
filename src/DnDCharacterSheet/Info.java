package DnDCharacterSheet;

import java.lang.*;

/**
 *
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
    private int age;
    private int wt;

    /**
     *
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
                String alignment, String size, String background, int age, int wt) {
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
    }

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
        this.age = 0;
        this.wt = 0;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getHt() {
        return ht;
    }

    /**
     *
     * @return
     */
    public String getEyes() {
        return eyes;
    }

    /**
     *
     * @return
     */
    public String getSkin() {
        return skin;
    }

    /**
     *
     * @return
     */
    public String getHair() {
        return hair;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @return
     */
    public String getDeity() {
        return deity;
    }

    /**
     *
     * @return
     */
    public String getHomeland() {
        return homeland;
    }

    /**
     *
     * @return
     */
    public String getLanguages() {
        return languages;
    }

    /**
     *
     * @return
     */
    public String getAlignment() {
        return alignment;
    }

    /**
     *
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @return
     */
    public String getBackground() {
        return background;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return
     */
    public int getWt() {
        return wt;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param ht
     */
    public void setHt(String ht) {
        this.ht = ht;
    }

    /**
     *
     * @param eyes
     */
    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    /**
     *
     * @param skin
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }

    /**
     *
     * @param hair
     */
    public void setHair(String hair) {
        this.hair = hair;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @param deity
     */
    public void setDeity(String deity) {
        this.deity = deity;
    }

    /**
     *
     * @param homeland
     */
    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }

    /**
     *
     * @param languages
     */
    public void setLanguages(String languages) {
        this.languages = languages;
    }

    /**
     *
     * @param alignment
     */
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    /**
     *
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @param background
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @param wt
     */
    public void setWt(int wt) {
        this.wt = wt;
    }
}
