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
