package DnDCharacterSheet;

/**
 *
 */
public class Item {

    private String name;
    private float weight;
    private String category;
    private String description;
    private int amount;

    /**
     *
     * @param name
     * @param weight
     * @param category
     * @param description
     * @param amount
     */
    public Item(String name, float weight, String category, String description, int amount) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    /**
     *
     */
    public Item(){
        this.name = " ";
        this.weight = 0.0f;
        this.category = " ";
        this.description = " ";
        this.amount = 0;
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
    public float getWeight() {
        return weight;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param name
     */
    public void setItemName(String name) {
        this.name = name;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
