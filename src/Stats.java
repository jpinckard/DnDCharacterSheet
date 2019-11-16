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

    public void calcMod(int row){
        int tempMod = statsGrid[row][1];
        tempMod = tempMod - 10;
        tempMod = tempMod / 2;
        setStat(row, 1, tempMod);
    }
}
