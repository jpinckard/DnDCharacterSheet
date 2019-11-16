package DnDCharacterSheet;

public class Item {

    private String name;
    private float weight;
    private String category;
    private String description;
    private int amount;

    public Item(String name, float weight, String category, String description, int amount) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public Item(){
        this.name = " ";
        this.weight = 0.0f;
        this.category = " ";
        this.description = " ";
        this.amount = 0;
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
}
