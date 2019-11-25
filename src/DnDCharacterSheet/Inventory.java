package DnDCharacterSheet;

import java.util.*;

/**
 *
 */
public class Inventory {

    private Currency money;
    private Equipment charEquipment;
    private ArrayList<Item> items = new ArrayList<Item>();
    private float carriedWeight;
    private String treasure;

    /**
     * Parameterized constructor for Inventory class.
     * @param money
     * @param charEquipment
     * @param items
     * @param carriedWeight
     */
    public Inventory(Currency money, Equipment charEquipment, ArrayList<Item> items, float carriedWeight) {
        this.money = money;
        this.charEquipment = charEquipment;
        this.items = items;
        this.carriedWeight = carriedWeight;
    }

    /**
     * Default constructor for Inventory class.
     */
    public Inventory() {
        this.money = new Currency();
        this.charEquipment = new Equipment();
        this.items = new ArrayList<Item>();
        this.carriedWeight = 0.0f;
    }

    public DnDCharacterSheet.Currency getMoney() {
        return money;
    }

    public Equipment getCharEquipment() {
        return charEquipment;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public float getCarriedWeight() {
        return carriedWeight;
    }

    public void setMoney(Currency money) {
        this.money = money;
    }

    public void setCharEquipment(Equipment charEquipment) {
        this.charEquipment = charEquipment;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCarriedWeight(float carriedWeight) {
        this.carriedWeight = carriedWeight;
    }

    /**
     * Method to calculate the total carried weight of the inventory.
     */
    public void calcCarriedWeight(){

        Iterator<Item> itemsIterator = items.iterator();
        float tempWeight = 0.0f;

        while (itemsIterator.hasNext()) {
           tempWeight = tempWeight + (itemsIterator.next()).getWeight();
        }

        tempWeight = tempWeight + charEquipment.getWeight();
        tempWeight = tempWeight + money.getWeight();

        setCarriedWeight(tempWeight);
    }
}
