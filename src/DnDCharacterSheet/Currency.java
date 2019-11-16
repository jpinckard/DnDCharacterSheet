package DnDCharacterSheet;

public class Currency
{
    private int pp;
    private int gp;
    private int ep;
    private int sp;
    private int cp;

    public Currency()
    {
        pp = 0;
        gp = 0;
        ep = 0;
        sp = 0;
        cp = 0;
    }

    public Currency(int p, int g, int e, int s, int c)
    {
        pp = p;
        gp = g;
        ep = e;
        sp = s;
        cp = c;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getCp() {
        return cp;
    }

    public int getGp() {
        return gp;
    }

    public int getEp() {
        return ep;
    }

    public int getPp() {
        return pp;
    }

    public int getSp() {
        return sp;
    }

    public double getTotalGp()
    {
        return (gp + 10*pp + .5*ep + .1*sp + .01*cp);
    }

    public void incPp()
    {
        pp += 1;
    }

    public void incGp()
    {
        gp += 1;
    }

    public void incEp()
    {
        ep += 1;
    }

    public void incSp()
    {
        sp += 1;
    }

    public void incCp()
    {
        cp += 1;
    }

    public void decPp()
    {
        pp -= 1;
    }

    public void decGp()
    {
        gp -= 1;
    }

    public void decEp()
    {
        ep -= 1;
    }

    public void decSp()
    {
        sp -= 1;
    }

    public void decCp()
    {
        cp -= 1;
    }
}
