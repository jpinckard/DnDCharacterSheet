package DnDCharacterSheet;

import java.util.ArrayList;

public class SpellSheet {
    private ArrayList<Spell> spells;
    private int[] cspd;
    private int[] abilityBonus;
    private int[] misc;
    private int[] known;
    private int spClass;
    private int spAbility;
    private int spOther;
    private int spCurrent;

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public int[] getCspd() {
        return cspd;
    }

    public void setCspd(int[] cspd) {
        this.cspd = cspd;
    }

    public int[] getAbilityBonus() {
        return abilityBonus;
    }

    public void setAbilityBonus(int[] abilityBonus) {
        this.abilityBonus = abilityBonus;
    }

    public int[] getMisc() {
        return misc;
    }

    public void setMisc(int[] misc) {
        this.misc = misc;
    }

    public int[] getKnown() {
        return known;
    }

    public void setKnown(int[] known) {
        this.known = known;
    }

    public int getSpClass() {
        return spClass;
    }

    public void setSpClass(int spClass) {
        this.spClass = spClass;
    }

    public int getSpAbility() {
        return spAbility;
    }

    public void setSpAbility(int spAbility) {
        this.spAbility = spAbility;
    }

    public int getSpOther() {
        return spOther;
    }

    public void setSpOther(int spOther) {
        this.spOther = spOther;
    }

    public int getSpCurrent() {
        return spCurrent;
    }

    public void setSpCurrent(int spCurrent) {
        this.spCurrent = spCurrent;
    }
}
