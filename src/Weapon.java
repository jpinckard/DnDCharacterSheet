public class Weapon
{
    private String damage;
    private int range;
    private boolean martial; //0 for simple, 1 for martial
    private boolean ranged; //0 for melee, 1 for ranged
    private String baseMod; //Specify if it used strength or dex.

    public Weapon()
    {
        damage = "";
        range = 0;
        martial = false;
        ranged = false;
        baseMod = "Strength";
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public void setBaseMod(String baseMod) {
        this.baseMod = baseMod;
    }

    public void setMartial(boolean martial) {
        this.martial = martial;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    public String getDamage()
    {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public String getBaseMod() {
        return baseMod;
    }

    public boolean isMartial() {
        return martial;
    }

    public boolean isRanged() {
        return ranged;
    }
}








