package DnDCharacterSheet;

import java.util.*;

public class Inventory {

    private DnDCharacterSheet.Currency money;
    private Equipment charEquipment;
    private ArrayList<Item> items = new ArrayList<Item>();
    private float carriedWeight;

    public Inventory(DnDCharacterSheet.Currency money, Equipment charEquipment, ArrayList<Item> items, float carriedWeight) {
        this.money = money;
        this.charEquipment = charEquipment;
        this.items = items;
        this.carriedWeight = carriedWeight;
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
