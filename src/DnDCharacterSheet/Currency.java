package DnDCharacterSheet;

/**
 * This class keeps track of the currency in all various denominations.
 */
public class Currency
{
    private int pp; // Platinum Pieces (1pp = 10gp)
    private int gp; // Gold Pieces
    private int ep; // Electrum Pieces (1ep = .5gp)
    private int sp; // Silver Pieces (1sp = .1gp)
    private int cp; // Copper Pieces (1cp = .01gp)

    /**
     * Default constructor for the Currency class. Sets all denominations to 0
     */
    public Currency()
    {
        pp = 0;
        gp = 0;
        ep = 0;
        sp = 0;
        cp = 0;
    }

    /**
     * Parameterized constructor for input amount of cash right off the bat.
     * @param p number of platinum pieces
     * @param g number of gold pieces
     * @param e number of electrum pieces
     * @param s number of silver pieces
     * @param c number of copper pieces
     */
    public Currency(int p, int g, int e, int s, int c)
    {
        pp = p;
        gp = g;
        ep = e;
        sp = s;
        cp = c;
    }

    /**
     * Calculates the weight of money carried. 50 gold pieces of any denomination is equal to a lb.
     * @return weight of money carried in lbs.
     */
    public float getWeight()
    {
        return (pp/50 + gp/50 + ep/50 + sp/50 + cp/50);
    }

    /**
     * Sets amount of copper pieces.
     * @param cp number of copper pieces
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Sets amount of electrum pieces.
     * @param ep number of electrum pieces
     */
    public void setEp(int ep) {
        this.ep = ep;
    }

    /**
     * Sets amount of gold pieces.
     * @param gp number of gold pieces
     */
    public void setGp(int gp) {
        this.gp = gp;
    }

    /**
     * Sets amount of platinum pieces.
     * @param pp amount of platinum pieces
     */
    public void setPp(int pp) {
        this.pp = pp;
    }

    /**
     * Sets amount of silver pieces.
     * @param sp amount of silver pieces
     */
    public void setSp(int sp) {
        this.sp = sp;
    }

    /**
     * Returns amount of copper pieces.
     * @return amount of copper pieces
     */
    public int getCp() {
        return cp;
    }

    /**
     * Returns amount of gold pieces.
     * @return amount of gold pieces
     */
    public int getGp() {
        return gp;
    }

    /**
     * Returns amount of electrum pieces.
     * @return amount of electrum pieces
     */
    public int getEp() {
        return ep;
    }

    /**
     * Returns amount of platinum pieces.
     * @return amount of platinum pieces
     */
    public int getPp() {
        return pp;
    }

    /**
     * Returns amount of silver pieces.
     * @return amount of silver pieces
     */
    public int getSp() {
        return sp;
    }

    /**
     * Returns equivalent gold value of all currencies combined.
     * @return gold value of all currency
     */
    public double getTotalGp()
    {
        return (gp + 10*pp + .5*ep + .1*sp + .01*cp);
    }

    /**
     * increments amount of pp
     */
    public void incPp()
    {
        pp += 1;
    }

    /**
     * increments amount of gp
     */
    public void incGp()
    {
        gp += 1;
    }

    /**
     * increments amount of ep
     */
    public void incEp()
    {
        ep += 1;
    }

    /**
     * increments amount of sp
     */
    public void incSp()
    {
        sp += 1;
    }

    /**
     * increments amount of cp
     */
    public void incCp()
    {
        cp += 1;
    }

    /**
     * decrements amount of pp
     */
    public void decPp()
    {
        pp -= 1;
    }

    /**
     * decrements amount of gp
     */
    public void decGp()
    {
        gp -= 1;
    }

    /**
     * decrements amount of ep
     */
    public void decEp()
    {
        ep -= 1;
    }

    /**
     * decrements amount of sp
     */
    public void decSp()
    {
        sp -= 1;
    }

    /**
     * decrements amount of cp
     */
    public void decCp()
    {
        cp -= 1;
    }
}
