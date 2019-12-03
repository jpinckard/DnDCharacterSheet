package DnDCharacterSheet;

import java.util.ArrayList;

/**
 * This is the master CharacterSheet class that aggregates all of the component classes.
 * The control interacts with everything else through this class.
 *
 * @author Alex Abel-Boozer, Joy Pinckard, Fred Snopl, Jared Taylor
 * @version 0.1
 * @since 2019-12-05
 */
public class CharacterSheet {
    private String name;
    private int level;
    private ArrayList<Profession> professions;
    private Profession profession;
    private ArrayList<Race> races;
    private Race race;
    private Info info;
    private Story story;
    private HitPoints hitPoints;
    private Skill[] skills;
    private Stats stats;
    private WizText wizText;
    private Inventory inventory;
    private SpellSheet spellSheet;
    //private SavingThrow[] savingThrows;
    private int[] savingThrows;
    private MiscStats miscStats;
    private int exp;
    //private ArrayList<Feature> features;
    //private Story charstory;
    private int proficiency;
    private Currency currency;

    // These represent the ROW of the stats array
    private static final int STR = 0;
    private static final int DEX = 1;
    private static final int CON = 2;
    private static final int INT = 3;
    private static final int WIS = 4;
    private static final int CHA = 5;

    public CharacterSheet() {
        this.name = "";
        this.level = 1;
        this.professions = new ArrayList<Profession>();
        this.profession = new Profession();
        this.races = new ArrayList<Race>();
        this.race = new Race();
        this.info = new Info();
        this.story = new Story();
        this.hitPoints = new HitPoints();
        this.skills = new Skill[] {
                new Skill("Acrobatics", DEX, false, 0, 0, 0),
                new Skill("Animal Handling", WIS, false, 0, 0, 0),
                new Skill("Arcana", INT, false, 0, 0, 0),
                new Skill("Athletics", STR, false, 0, 0, 0),
                new Skill("Deception", CHA, false, 0, 0, 0),
                new Skill("History", INT, false, 0, 0, 0),
                new Skill("Insight", WIS, false, 0, 0, 0),
                new Skill("Intimidation", CHA, false, 0, 0, 0),
                new Skill("Investigation", INT, false, 0, 0, 0),
                new Skill("Medicine", WIS, false, 0, 0, 0),
                new Skill("Nature", INT, false, 0, 0, 0),
                new Skill("Perception", WIS, false, 0, 0, 0),
                new Skill("Performance", CHA, false, 0, 0, 0),
                new Skill("Persuasion", CHA, false, 0, 0, 0),
                new Skill("Religion", INT, false, 0, 0, 0),
                new Skill("Sleight of Hand", DEX, false, 0, 0, 0),
                new Skill("Stealth", DEX, false, 0, 0, 0),
                new Skill("Survival", WIS, false, 0, 0, 0)
        };
        this.stats = new Stats();
        this.wizText = new WizText();
        this.inventory = new Inventory();
        this.spellSheet = new SpellSheet();
        //this.savingThrows = new int[6];
        this.miscStats = new MiscStats();
        this.exp = 0;
        //this.features = new ArrayList<Feature>();
        //this.charstory = new Story();
        this.currency = new Currency();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(ArrayList<Profession> professions) {
        this.professions = professions;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public HitPoints getHp() {
        return hitPoints;
    }

    public void setHp(HitPoints hp) {
        this.hitPoints = hp;
    }

    public Skill[] getSkills() {
        return skills;
    }

    /**
     * Used to calculate the derived total skill stat from the stat modifier, the assigned skill proficiency bonus,
     * the assigned skill expertise bonus, and the assign skill miscellaneous bonus.
     * @param skillnum
     * @return
     */
    public int getSkillTotal(int skillnum){
        //to calculate the skill total we must add the skill modifier to the proficiency, expertise, and misc bonuses in skill
        return (stats.getStat(skills[skillnum].getModType(), 1) + skills[skillnum].getProf() + skills[skillnum].getExpert() + skills[skillnum].getMisc());
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public Stats getStats(){return stats;}

    public void setStat(int row, int col, int tempStat) {
        stats.setStat(row, col, tempStat);
        stats.calcTotal(race);
        stats.calcMod(row);
    }

    public WizText getWizText() {
        return wizText;
    }

    public void setWizText(WizText wizText) {
        this.wizText = wizText;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public SpellSheet getSpellSheet() {
        return spellSheet;
    }

    public void setSpellSheet(SpellSheet spellSheet) {
        this.spellSheet = spellSheet;
    }

    public int[] getSavingThrows() {
        calcSavingThrows();
        return savingThrows;
    }

    /**
     * This method calculates the saving throw values from the stats class.
     */
    public void calcSavingThrows()
    {
        savingThrows = new int[]{0,0,0,0,0,0};
        for(int i = 0; i < 6; i++)
        {
            savingThrows[i] = stats.getStat(i,1);
            /*
            if((profession.getSavingThrows())[i] == 1)
            {
                savingThrows[i] = savingThrows[i] + miscStats.getProfBonus();
            }
             */
        }
    }

    public MiscStats getMiscStats() {
        return miscStats;
    }

    public void setMiscStats(MiscStats miscStats) {
        this.miscStats = miscStats;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
/*
    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
*/
    public void setProficiency(int prof){
        proficiency = prof;
    }

    public int getProficiency(){ return proficiency; }

    public Currency getCurrency(){ return currency; }
}
