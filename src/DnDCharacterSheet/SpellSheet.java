package DnDCharacterSheet;

import java.util.ArrayList;

public class SpellSheet {
    private ArrayList<Spell> spells;
    private int[] cspd1;
    private int[] abilityBonus1;
    private int[] misc1;
    private int[] known1;
    private int[] cspd2;
    private int[] abilityBonus2;
    private int[] misc2;
    private int[] known2;
    private int spTotal1;
    private int spClass1;
    private int spAbility1;
    private int spOther1;
    private int spCurrent1;
    private int spTotal2;
    private int spClass2;
    private int spAbility2;
    private int spOther2;
    private int spCurrent2;
    private String castClass1;
    private String castClass2;
    private String castLevel1;
    private String castLevel2;
    private String patron1;
    private String patron2;
    private String domain1;
    private String subDomain1;
    private String domain2;
    private String subDomain2;
    private String specialtySchool;
    private String specialtyFocus;
    private String specialtyProhibited1;
    private String specialtyProhibited2;

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public int[] getCspd1() {
        return cspd1;
    }

    public void setCspd1(int[] cspd) {
        this.cspd1 = cspd;
    }

    public int[] getAbilityBonus1() {
        return abilityBonus1;
    }

    public void setAbilityBonus1(int[] abilityBonus) {
        this.abilityBonus1 = abilityBonus;
    }

    public int[] getMisc1() {
        return misc1;
    }

    public void setMisc1(int[] misc) {
        this.misc1 = misc;
    }

    public int[] getKnown1() {
        return known1;
    }

    public void setKnown1(int[] known) {
        this.known1 = known;
    }

    public int getSpClass1() {
        return spClass1;
    }

    public void setSpClass1(int spClass) {
        this.spClass1 = spClass;
    }

    public int getSpAbility1() {
        return spAbility1;
    }

    public void setSpAbility1(int spAbility) {
        this.spAbility1 = spAbility;
    }

    public int getSpOther1() {
        return spOther1;
    }

    public void setSpOther1(int spOther) {
        this.spOther1 = spOther;
    }

    public int getSpCurrent1() {
        return spCurrent1;
    }

    public void setSpCurrent1(int spCurrent) {
        this.spCurrent1 = spCurrent;
    }
}
