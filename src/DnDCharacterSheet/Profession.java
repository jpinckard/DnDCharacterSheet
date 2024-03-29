package DnDCharacterSheet;

import java.util.ArrayList;

public class Profession {

    private String name;
    private int hd;
    private String armor;
    private String weapon;
    private String tools;
    private int[] savingThrows;
    private ArrayList<Item> startingEquipment;
    private String skills;

    public Profession()
    {

    }

    public Profession(String name, int hd, int[] savingThrows)
    {
        this.name = name;
        this.hd = hd;
        this.savingThrows = savingThrows;
    }


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

    public int[] getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(int[] savingThrows) {
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
