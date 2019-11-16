package DnDCharacterSheet;

import java.util.ArrayList;

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
    private ArrayList<Feature> features;
    private Story charstory;

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

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
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

    public void calcSavingThrows()
    {
        savingThrows = new int[]{0,0,0,0,0,0};
        for(int i = 0; i < 6; i++)
        {
            savingThrows[i] = stats.getStat(i,1);
            if((profession.getSavingThrows())[i] == 1)
            {
                savingThrows[i] = savingThrows[i] + miscStats.getProfBonus();
            }
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

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
}
