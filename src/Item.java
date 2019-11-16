public class Item {

    private String itemName;
    private float weight;
    private String category;
    private String description;
    private int amount;

    public Item(String itemName, float weight, String category, String description, int amount) {
        this.itemName = itemName;
        this.weight = weight;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
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

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
