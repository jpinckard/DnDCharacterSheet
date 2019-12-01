package DnDCharacterSheet;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteHandler {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\test.db";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public static Connection Setup(){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return conn;
    }

    /**
     * Returns a list of all spells.
     * @param connection
     * @return
     * @throws Exception
     */
    public static ArrayList<Spell> LoadSpells(Connection connection) throws Exception
    {
        ArrayList<Spell> spells = new ArrayList<Spell>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = "SELECT * FROM SPELLS";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                // Get Spell
                int id          = rs.getInt(1);
                String name     = rs.getString(2);
                String description = rs.getString(3);
                int level       = rs.getInt(4);
                int prep        = rs.getInt(5);
                int used        = rs.getInt(6);
                String school      = rs.getString(7);
                String duration    = rs.getString(8);
                String range       = rs.getString(9);
                String save        = rs.getString(10);
                String components  = rs.getString(11);

                Spell spell = new Spell(name, description, level, prep, used, school, duration, range, save, components);
                spells.add(spell);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return spells;
    }

    /**
     * Returns a list of all weapons.
     * @param connection
     * @return
     * @throws Exception
     */
    public static ArrayList<Weapon> LoadWeapons(Connection connection) throws Exception
    {
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = "SELECT * FROM WEAPONS";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                // Get Weapon
                String name         = rs.getString(1);
                float cost         = rs.getFloat(2);
                float weight        = rs.getFloat(3);
                String description  = rs.getString(4);
                String category     = rs.getString(5);
                int amount          = rs.getInt(6);

                int damage        = rs.getInt(8);
                int range        = rs.getInt(9);
                boolean martial = rs.getBoolean(10);
                boolean ranged = rs.getBoolean(11);
                boolean finesse = rs.getBoolean(12);
                String type      = rs.getString(13);

                /* String name, float weight, String category, String description, int amount, float cost, String damage, int range, boolean martial, boolean ranged, boolean finesse, String type */
                Weapon weapon = new Weapon(name, weight, category, description, amount, cost, Integer.toString(damage), range, martial, ranged, finesse, type);
                weapons.add(weapon);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return weapons;
    }

    /**
     * Returns a list of all items.
     * @param connection
     * @return
     * @throws Exception
     */
    public static ArrayList<Item> LoadInventory(Connection connection) throws Exception
    {
        ArrayList<Item> inventory = new ArrayList<Item>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = "SELECT * FROM INVENTORY";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                // Get Item
                String name         = rs.getString(1);
                String description  = rs.getString(2);
                int amount          = rs.getInt(3);
                float weight        = rs.getFloat(4);
                float cost         = rs.getFloat(5);
                String category     = rs.getString(6);

                Item item = new Item(name, weight, category, description, amount, cost);
                inventory.add(item);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return inventory;
    }

    /**
     * Returns a list of all armor.
     * @param connection
     * @return
     * @throws Exception
     */
    public static ArrayList<Armor> LoadArmor(Connection connection) throws Exception
    {
        ArrayList<Armor> armor = new ArrayList<Armor>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = "SELECT * FROM ARMOR";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                String name         = rs.getString(1);
                float cost         = rs.getFloat(2);
                float weight        = rs.getFloat(3);
                String description  = rs.getString(4);
                String category     = rs.getString(5);
                int amount          = rs.getInt(6);

                int ac          = rs.getInt(6);
                String group    = rs.getString(7);
                boolean stealth = rs.getBoolean(8);


                Armor a = new Armor(name, weight, category, description, amount, cost, ac, group, stealth);
                armor.add(a);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return armor;
    }

    /**
     * Adds a spell to the list of all spells.
     * @param connection
     * @param spell
     * @throws Exception
     */
    public static void AddSpell(Connection connection, Spell spell) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = new StringBuilder().append("INSERT INTO SPELLS (NAME, DESCRIPTION, LEVEL, PREP, USED, SCHOOL, DURATION, RANGE, SAVE, COMPONENTS) VALUES (\"").append(spell.getName()).append("\",\"").append(spell.getDescription()).append("\",").append(Integer.toString(spell.getLevel())).append(",").append(Integer.toString(spell.getPrep())).append(",").append(Integer.toString(spell.getUsed())).append(",\"").append(spell.getSchool()).append("\",\"").append(spell.getDuration()).append("\",\"").append(spell.getRange()).append("\",\"").append(spell.getSave()).append("\",\"").append(spell.getComponents()).append("\");").toString();

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * Adds an item to the inventory.
     * @param connection
     * @param item
     * @throws Exception
     */
    public static void AddItem(Connection connection, Item item) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;


            sql = new StringBuilder().append("INSERT INTO INVENTORY (AMOUNT, CATEGORY, DESCRIPTION, NAME, WEIGHT) VALUES (\"").append(Integer.toString(item.getAmount())).append("\",\"").append(item.getCategory()).append("\",").append(item.getDescription()).append(",").append(item.getName()).append(",").append(Float.toString(item.getWeight())).append("\");").toString();

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * Sorts a table by a filter.
     * @param table
     * @param filter
     * @throws Exception
     */
    public static void Sort(String table, String filter) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;

            sql = "SELECT * FROM " + table + " ORDER BY " + filter + " ASC";

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * Gets all distinct categories from all tables.
     * @param connection
     * @return
     * @throws Exception
     */
    public static ArrayList<String> GetCategories(Connection connection) throws Exception
    {

        ArrayList<String> categories = new ArrayList<String>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql;

            sql = "SELECT DISTINCT CATEGORY FROM INVENTORY"; //, WEAPONS, ARMOR
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){categories.add(rs.getString("Category"));}

            sql = "SELECT DISTINCT CATEGORY FROM WEAPONS"; //, WEAPONS, ARMOR
            rs = stmt.executeQuery(sql);
            while(rs.next()){categories.add(rs.getString("Category"));}

            sql = "SELECT DISTINCT CATEGORY FROM ARMOR"; //, WEAPONS, ARMOR
            rs = stmt.executeQuery(sql);
            while(rs.next()){categories.add(rs.getString("Category"));}

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return categories;
    }

    /**
     * Filters table results by category.
     * @param connection
     * @param element
     * @return
     * @throws Exception
     */
    public static ArrayList<Item> Filter(Connection connection, String element ) throws Exception
    {

        ArrayList<Item> items = new ArrayList<Item>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql;

            sql = "SELECT * FROM INVENTORY WHERE CATEGORY = \"" + element + "\" ORDER BY NAME ASC" ; //, WEAPONS, ARMOR // '" + element + "'

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                String description  = rs.getString(3);
                int amount          = rs.getInt(4);
                float weight        = rs.getFloat(5);
                float cost         = rs.getFloat(6);
                String category     = rs.getString(7);

                Item item = new Item(name, weight, category, description, amount, cost);
                System.out.println("Name: " + name);
                items.add(item);
            }

            sql = "SELECT * FROM WEAPONS WHERE CATEGORY = \"" + element + "\" ORDER BY NAME ASC" ; //, WEAPONS, ARMOR
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                float cost         = rs.getFloat(3);
                float weight        = rs.getFloat(4);
                String description  = rs.getString(5);
                String category     = rs.getString(6);
                int amount          = rs.getInt(7);

                int damage        = rs.getInt(8);
                int range        = rs.getInt(9);
                boolean martial = rs.getBoolean(10);
                boolean ranged = rs.getBoolean(11);
                boolean finesse = rs.getBoolean(12);
                String type      = rs.getString(13);

                Weapon weapon = new Weapon(name, weight, category, description, amount, cost, Integer.toString(damage), range, martial, ranged, finesse, type);

                items.add(weapon);
            }
            sql = "SELECT * FROM ARMOR WHERE CATEGORY = \"" + element + "\" ORDER BY NAME ASC"; //, WEAPONS, ARMOR

            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                float cost         = rs.getFloat(3);
                float weight        = rs.getFloat(4);
                String description  = rs.getString(5);
                String category     = rs.getString(6);
                int amount          = rs.getInt(7);

                int ac          = rs.getInt(8);
                String group    = rs.getString(9);
                boolean stealth = rs.getBoolean(10);

                Armor armor = new Armor(name, weight, category, description, amount, cost, ac, group, stealth);
                items.add(armor);
            }

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return items;
    }

    /**
     * Loads inventory items by name and category.
     * @param connection
     * @param table
     * @param names
     * @return
     * @throws Exception
     */
    public static ArrayList<Item> LoadItemsFromSave(Connection connection, String table, String names) throws Exception{

        ArrayList<Item> items = new ArrayList<Item>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql;


            sql = "SELECT * FROM INVENTORY WHERE NAME IN (" + names + ")";

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                String description  = rs.getString(3);
                int amount          = rs.getInt(4);
                float weight        = rs.getFloat(5);
                float cost         = rs.getFloat(6);
                String category     = rs.getString(7);

                Item item = new Item(name, weight, category, description, amount, cost);
                System.out.println("Name: " + name);
                items.add(item);
            }

            sql = "SELECT * FROM WEAPONS WHERE NAME IN (" + names + ") ORDER BY NAME ASC";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                float cost         = rs.getFloat(3);
                float weight        = rs.getFloat(4);
                String description  = rs.getString(5);
                String category     = rs.getString(6);
                int amount          = rs.getInt(7);

                int damage        = rs.getInt(8);
                int range        = rs.getInt(9);
                boolean martial = rs.getBoolean(10);
                boolean ranged = rs.getBoolean(11);
                boolean finesse = rs.getBoolean(12);
                String type      = rs.getString(13);

                Weapon weapon = new Weapon(name, weight, category, description, amount, cost, Integer.toString(damage), range, martial, ranged, finesse, type);

                items.add(weapon);
            }

            sql = "SELECT * FROM ARMOR WHERE NAME IN (" + names + ") ORDER BY NAME ASC";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name         = rs.getString(2);
                float cost         = rs.getFloat(3);
                float weight        = rs.getFloat(4);
                String description  = rs.getString(5);
                String category     = rs.getString(6);
                int amount          = rs.getInt(7);

                int ac          = rs.getInt(8);
                String group    = rs.getString(9);
                boolean stealth = rs.getBoolean(10);

                Armor armor = new Armor(name, weight, category, description, amount, cost, ac, group, stealth);
                items.add(armor);
            }

            System.out.println("\nCommand: " + sql + "\n");

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return items;
    }

}
