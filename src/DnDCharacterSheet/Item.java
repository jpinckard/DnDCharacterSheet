package DnDCharacterSheet;

/**
 *The item class holds all the necessary information for an Item object.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Item {

    private String name; //string associated with the item's name
    private float weight; //float associated with the item's weight
    private String category; //string associated with the item's category
    private String description; //string associated with the item's description
    private int amount; //int associated with the item's amount
    private float cost; // Cost in gold pieces

    /**
     *Parameterized constructor for the Item class.
     *
     * @param name - string for item name
     * @param weight - float for the weight of the item
     * @param category - string for the category of the item
     * @param description - string for the description of the item
     * @param amount - int for the amount of the item
     * @param cost - float for the cost of the item
     */
    public Item(String name, float weight, String category, String description, int amount, float cost) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.cost = cost;
    }

    /**
     *Default constructor for the Item class.
     */
    public Item(){
        this.name = " ";
        this.weight = 0.0f;
        this.category = " ";
        this.description = " ";
        this.amount = 0;
        cost = 0.0f;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }
}
