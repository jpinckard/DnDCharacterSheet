public class MiscStats {

    private int profBonus;
    private int[] AC = new int[6];
    private int[] init = new int[3];
    private int[] speed = new int[6];
    private int carryLoad;

    public MiscStats(int profBonus, int[] AC, int[] init, int[] speed, int carryLoad) {
        this.profBonus = profBonus;
        this.AC = AC;
        this.init = init;
        this.speed = speed;
        this.carryLoad = carryLoad;
    }

    public int getProfBonus() {
        return profBonus;
    }

    public int[] getAC() {
        return AC;
    }

    public int[] getInit() {
        return init;
    }

    public int[] getSpeed() {
        return speed;
    }

    public int getCarryLoad() {
        return carryLoad;
    }

    public void setProfBonus(int profBonus) {
        this.profBonus = profBonus;
    }

    public void setAC(int[] AC) {
        this.AC = AC;
    }

    public void setInit(int[] init) {
        this.init = init;
    }

    public void setSpeed(int[] speed) {
        this.speed = speed;
    }

    public void setCarryLoad(int carryLoad) {
        this.carryLoad = carryLoad;
    }

    public void calcAC(int dexMod)
    {

    }

    public void calcInit()
    {

    }

    public void calcCarryLoad(){

    }
}
