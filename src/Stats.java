public class Stats {

    private int[][] statsGrid = new int[6][6];

    public Stats(int[][] statsGrid) {
        this.statsGrid = statsGrid;
    }

    public int getStat(int row, int col) {
        return statsGrid[row][col];
    }

    public void setStat(int row, int col, int tempStat) {
        this.statsGrid[row][col] = tempStat;
    }

    public void calcTotal(Race tempRace){
        int[] boostTemp = tempRace.getStatBoost();

        for (int i = 0; i < 6; i++){
            statsGrid[i][0] = statsGrid[i][2] + boostTemp[i];
        }
    }

    public void calcMod(int row){
        int mod;
        double tempMod = statsGrid[row][0];

        tempMod = tempMod - 10;
        tempMod = tempMod / 2;
        mod = (int) Math.round(Math.floor(tempMod));
        setStat(row, 1, mod);
    }
}
