package DnDCharacterSheet;

import java.util.ArrayList;

public class Equipment
{
    private Armor armor;
    private ArrayList<Weapon> weaponList;
    private ArrayList<Item> ammoList;
    private boolean shield;

    public Equipment()
    {
        armor = null;
        weaponList = null;
        shield = false;
    }

    public float getWeight()
    {
        float weight = armor.getWeight();
        for(Weapon x : weaponList)
        {
            weight = weight + x.getWeight();
        }
        for(Item x : ammoList)
        {
            weight = weight + x.getWeight();
        }

        return weight;
    }

    public String getArmorType()
    {
        return armor.getaGroup();
    }

    

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public Armor getArmor() {
        return armor;
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public boolean isShield() {
        return shield;
    }
}
