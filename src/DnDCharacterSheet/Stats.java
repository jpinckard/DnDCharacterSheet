package DnDCharacterSheet;

import java.security.PrivilegedActionException;

/**
 *A class for producing a 2d array of stats for a character. The rows correspond to Strength, Dexterity, Constitution,
 * Intelligence, Wisdom, and Charisma from 0-5 in the array. The columns are total stat, modifier, base stat, racial bonus,
 * magical bonus, and temporary effects from 0-5 in the array.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class Stats {

    private int[][] statsGrid;//2d array for holding the stats


    public Stats(){
         statsGrid = new int[6][6];
    }

    /**
     *Constructs a new Stats object.
     *
     * @param statsGrid - a 2d array of ints for the stats
     */
    public Stats(int[][] statsGrid) {
        this.statsGrid = statsGrid;
    }

    public int getStat(int row, int col) {
        return statsGrid[row][col];
    }

    public void setStat(int row, int col, int tempStat) {
        this.statsGrid[row][col] = tempStat;
        //System.out.println("The stat has been updated to: " + statsGrid[row][col]);
    }

    /**
     *Calculates the total for stats based on the base stat, the race boost, the misc, and the temp.
     *
     * @param tempRace - a Race used for the race boost
     */
    public void calcTotal(Race tempRace){

        // take out the race thing for now for testing

       // int[] boostTemp = tempRace.getStatBoost();//temporary array to hold stat boost from Race class

        for (int i = 0; i < 6; i++){
            //statsGrid[i][0] = statsGrid[i][2] + boostTemp[i];
            statsGrid[i][0] = statsGrid[i][2] + statsGrid[i][3] + statsGrid[i][4] + statsGrid[i][5];
        }
        System.out.println("The total has been updated to: " + statsGrid[0][0]);
    }

    /**
     *Calculates the mod for a particular stat.
     *
     * @param row - the row number that correlates to a particular stat
     */
    public void calcMod(int row){
        int mod;//int for holding the calculated mod
        double tempMod = statsGrid[row][0];//double for calculating the mod

        tempMod = tempMod - 10;
        tempMod = tempMod / 2;
        mod = (int) Math.round(Math.floor(tempMod));
        setStat(row, 1, mod);
        System.out.println("The mod has been updated to: " + statsGrid[0][1]);
    }
}
