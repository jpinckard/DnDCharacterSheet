package DnDCharacterSheet;

/**
 *A class for producing a 2d array of stats for a character. The rows correspond to Strength, Dexterity, Constitution,
 * Intelligence, Wisdom, and Charisma from 0-5 in the array. The columns are total stat, modifier, base stat, racial bonus,
 * magical bonus, and temporary effects from 0-5 in the array.
 */
public class Stats {

    private int[][] statsGrid = new int[6][6];//2d array for holding the stats

    /**
     *Constructs a new Stats object.
     * @param statsGrid - a 2d array of ints for the stats
     */
    public Stats(int[][] statsGrid) {
        this.statsGrid = statsGrid;
    }

    /**
     * Returns a stat from the 2d array of stats.
     * @param row - the row number for the targeted stat
     * @param col - the column number for the targeted stat
     * @return the number for a stat
     */
    public int getStat(int row, int col) {
        return statsGrid[row][col];
    }

    /**
     *Sets an individual stat based on the parameter that is passed in.
     * @param row - the row number for the stat to set
     * @param col - the column number for the stat to set
     * @param tempStat - the number to set the stat to
     */
    public void setStat(int row, int col, int tempStat) {
        this.statsGrid[row][col] = tempStat;
    }

    /**
     *Calculates the total for stats based on the base stat, the race boost, the misc, and the temp.
     * @param tempRace - a Race used for the race boost
     */
    public void calcTotal(Race tempRace){
        int[] boostTemp = tempRace.getStatBoost();//temporary array to hold stat boost from Race class

        for (int i = 0; i < 6; i++){
            statsGrid[i][0] = statsGrid[i][2] + boostTemp[i];
            statsGrid[i][0] = statsGrid[i][0] + statsGrid[i][4];
            statsGrid[i][0] = statsGrid[i][0] + statsGrid[i][5];
        }
    }

    /**
     *Calculates the mod for a particular stat.
     * @param row - the row number that correlates to a particular stat
     */
    public void calcMod(int row){
        int mod;//int for holding the calculated mod
        double tempMod = statsGrid[row][0];//double for calculating the mod

        tempMod = tempMod - 10;
        tempMod = tempMod / 2;
        mod = (int) Math.round(Math.floor(tempMod));
        setStat(row, 1, mod);
    }
}
