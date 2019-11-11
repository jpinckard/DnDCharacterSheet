import java.util.ArrayList;

public class Profession {

    private String name;
    private int hd;
    private String armor;
    private String weapon;
    private String tools;
    private String savingThrows;
    private ArrayList<Item> startingEquipment;
    private String skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(String savingThrows) {
        this.savingThrows = savingThrows;
    }

    public ArrayList<Item> getStartingEquipment() {
        return startingEquipment;
    }

    public void setStartingEquipment(ArrayList<Item> startingEquipment) {
        this.startingEquipment = startingEquipment;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
