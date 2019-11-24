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
     *
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

    public Inventory() {
        this.money = new Currency();
        this.charEquipment = new Equipment();
        this.items = new ArrayList<Item>();
        this.carriedWeight = 0.0f;
    }

    /**
     *
     * @return
     */
    public DnDCharacterSheet.Currency getMoney() {
        return money;
    }

    /**
     *
     * @return
     */
    public Equipment getCharEquipment() {
        return charEquipment;
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     *
     * @return
     */
    public float getCarriedWeight() {
        return carriedWeight;
    }

    /**
     *
     * @param money
     */
    public void setMoney(Currency money) {
        this.money = money;
    }

    /**
     *
     * @param charEquipment
     */
    public void setCharEquipment(Equipment charEquipment) {
        this.charEquipment = charEquipment;
    }

    /**
     *
     * @param items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     *
     * @param carriedWeight
     */
    public void setCarriedWeight(float carriedWeight) {
        this.carriedWeight = carriedWeight;
    }

    /**
     *
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
