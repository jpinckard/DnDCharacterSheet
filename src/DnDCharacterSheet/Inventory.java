package DnDCharacterSheet;

import java.util.*;

/**
 *The inventory class keeps track of the character's money, equipment, and items, as well as
 * the carried weight of these. It contains methods to calculate this carried weight.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Inventory {

    private Currency money; //an object of Currency to associated with the character's money
    private Equipment charEquipment; //an object of Equipment associated with the character's equipment
    private ArrayList<Item> items = new ArrayList<Item>(); //an array list of Items to hold the character's items
    private float carriedWeight; //float associated with the carried weight
    private String treasure;

    /**
     * Parameterized constructor for Inventory class.
     *
     * @param money - an object of currency
     * @param charEquipment - an object of equipment
     * @param items - an array list of items
     * @param carriedWeight - a float for the carried weight
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
