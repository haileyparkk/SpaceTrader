package data;

import javafx.stage.Stage;
import objects.Region;
import objects.Universe;
import objects.Ship;

import java.util.ArrayList;

/**
 * This class represents a UserData object.
 * @author Mary Beth, Hailey Park, Andrew Harris
 * @version 1.0
 */
public class UserData {
    private static String name;
    private static String difficulty;
    private static double credit;
    private static int planetNum;
    private static int health;
    private static int skillPoints;
    private static int pilot;
    private static int fighter;
    private static int merchant;
    private static int engineer;
    private static Ship ship;
    private static Universe myUniverse;
    private static Region currentRegion;
    private static int selectedIndex;
    private static ArrayList<Upgrade> activeUpgrades;
    private static ArrayList<Upgrade> ownedUpgrades;
    private static int buffToPilot;
    private static int buffToEngineer;
    private static int buffToMerchant;
    private static int buffToFighter;
    private static boolean inEncounter;
    private static ArrayList<Good> ownedItems;
    private static boolean hasWinningItem = false;

    /**
     * This constructor constructs a User object
     * @param credit credit points
     * @param pilot pilot points
     * @param fighter fighter points
     * @param merchant merchant points
     * @param engineer engineer points
     */
    public void user(int credit, int pilot, int fighter, int merchant, int engineer, Stage primaryStage) {
        this.credit = credit;
        this.pilot = pilot;
        this.fighter = fighter;
        this.merchant = merchant;
        this.engineer = engineer;
        ship = new Ship(ShipModel.BIG_CHUNGUS, primaryStage);
    }

    /**
     * This method is a getter that gets the name.
     * @return a string representing the name
     */

    public static String getName() {
        return name;
    }

    /**
     * Getter method for difficulty.
     * @return string representing difficulty level
     */
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * This method is a getter that gets the skill point amount.
     * @return an int representing the skill point
     */

    public static int getSkillPoints() {
        return skillPoints;
    }

    /**
     * This method is a getter that gets the pilot.
     * @return an int representing the pilot
     */

    public static int getPilot() {
        return pilot;
    }

    /**
     * This method is a getter that gets the credit amount.
     * @return an int representing the credit amount
     */

    public static double getCredit() {
        return credit;
    }

    /**
     * This method is a getter that gets the fighter.
     * @return an int representing the fighter
     */

    public static int getFighter() {
        return fighter;
    }

    /**
     * This method is a getter that gets the merchant.
     * @return an int representing the merchant
     */

    public static int getMerchant() {
        return merchant;
    }

    /**
     * This method is a getter that gets the engineer.
     * @return an int representing the engineer
     */

    public static int getEngineer() {
        return engineer;
    }

    /**
     * This method is a getter that gets the planet number.
     * @return an int representing the planet number
     */

    public static int getPlanetNum() {
        return planetNum;
    }

    /**
     * This method is a getter that gets the health amount.
     * @return an int representing the health amount
     */

    public static int getHealth() {
        return health;
    }

    /**
     * gets the player's ship
     * @return ship
     */
    public static Ship getShip() {
        return ship;
    }

    /**
     * Getter for the player's Universe
     * @return Universe instance
     */
    public static Universe getUniverse() {
        return myUniverse;
    }

    /**
     * Getter for the player's current Region
     * @return Region instance
     */
    public static Region getCurrentRegion() {
        return currentRegion;
    }

    /**
     * Returns an arrayList containing all upgrades which are owned by the player, regardless
     * of whether they are equipped or not
     * @return arraylist of upgrades
     */
    public static ArrayList<Upgrade> getOwnedUpgrades() {
        return ownedUpgrades;
    }

    /**
     * Returns a list containing the player's currently active upgrades
     * @return  ArrayList of upgrades
     */
    public static ArrayList<Upgrade> getActiveUpgrades() {
        return activeUpgrades;
    }

    public static int getBuffToPilot() {
        return buffToPilot;
    }

    public static int getBuffToEngineer() {
        return buffToEngineer;
    }

    public static int getBuffToMerchant() {
        return buffToMerchant;
    }

    public static int getBuffToFighter() {
        return buffToFighter;
    }

    public static boolean getEncounterStatus() {
        return inEncounter;
    }

    public static ArrayList<Good> getOwnedItems() {
        if (ownedItems == null) {
            ownedItems = new ArrayList<Good>();
        }
        return ownedItems;
    }

    /**
     * This method is a setter that sets the name.
     * @param username a string representing the name
     * This method is a setter that sets the name.
     */

    public static void setName(String username) {
        name = username;
    }

    /**
     * This method is a setter that sets the skill point amount.
     * @param skillpoints an int representing skill point amount
     */

    public static void setSkillPoints(int skillpoints) {
        skillPoints = skillpoints;
    }

    /**
     * Setter method for difficulty.
     * @param difficultyLevel string representing the difficulty level
     */
    public static void setDifficulty(String difficultyLevel) {
        difficulty = difficultyLevel;
    }

    /**
     * This method is a setter that sets the pilot.
     * @param p an int representing pilot
     */

    public static void setPilot(int p) {
        pilot = p;
    }

    /**
     * This method is a setter that sets the fighter.
     * @param f an int representing fighter
     */

    public static void setFighter(int f) {
        fighter = f;
    }

    /**
     * This method is a setter that sets the merchant.
     * @param m an int representing merchant
     */

    public static void setMerchant(int m) {
        merchant = m;
    }

    /**
     * This method is a setter that sets the engineer.
     * @param e an int representing engineer
     */

    public static void setEngineer(int e) {
        engineer = e;
    }

    /**
     * This method is a setter that sets the credit.
     * @param c an int representing credit
     */

    public static void setCredit(double c) {
        credit = c;
    }

    /**
     * This method is a setter that sets the planet number.
     * @param pN an int representing planet number
     */

    public static void setPlanetNum(int pN) {
        planetNum = pN;
    }

    /**
     * This method is a setter that sets the health.
     * @param h an int representing health
     */

    public static void setHealth(int h) {
        health = h;
    }

    /**
     * Sets the player's current Universe
     * @param universe Universe instance
     */
    public static void setUniverse(Universe universe) {
        myUniverse = universe;
    }

    /**
     * Sets the player's current region
     * @param region Region instance
     */
    public static void setCurrentRegion(Region region) {
        if (UserData.getShip() != null && UserData.getShip().getCurrentFuel() > 5) {
            UserData.getShip().setCurrentFuel(UserData.getShip().getCurrentFuel()
                    - 10 + (UserData.getFighter()));
        }
        currentRegion = region;
    }

    /**
     * Sets the selected int
     * @param num int of the selected index
     */
    public static void setSelectedIndex(int num) {
        selectedIndex = num;
    }

    /**
     * Returns the selected int
     * @return the int
     */
    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static void setShip(Ship ship) {
        UserData.ship = ship;
    }

    public static void setEncounterStatus(boolean truthVal) {
        inEncounter = truthVal;
    }

    /**
     * Adds an upgrade to a player's current list of upgrades, and adjusts current skill levels
     * appropriately
     * @param upgrade Upgrade item to be added
     */
    public static void equipUpgrade(Upgrade upgrade) {
        if (activeUpgrades == null) {
            activeUpgrades = new ArrayList<>();
        }
        if (!activeUpgrades.contains(upgrade)) {
            activeUpgrades.add(upgrade);

            if (upgrade.getSkill().equals(Skill.ENGINEER)) {
                setEngineer(engineer + upgrade.getBuff());
                buffToEngineer += upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.FIGHTER)) {
                setFighter(fighter + upgrade.getBuff());
                buffToFighter += upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.MERCHANT)) {
                setMerchant(merchant + upgrade.getBuff());
                buffToMerchant += upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.PILOT)) {
                setPilot(pilot + upgrade.getBuff());
                buffToPilot += upgrade.getBuff();
            }
        }
    }

    public static void unequipUpgrade(Upgrade upgrade) {
        if (activeUpgrades != null && activeUpgrades.contains(upgrade)) {
            activeUpgrades.remove(upgrade);

            if (upgrade.getSkill().equals(Skill.ENGINEER)) {
                setEngineer(engineer - upgrade.getBuff());
                buffToEngineer -= upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.FIGHTER)) {
                setFighter(fighter - upgrade.getBuff());
                buffToFighter -= upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.MERCHANT)) {
                setMerchant(merchant - upgrade.getBuff());
                buffToMerchant -= upgrade.getBuff();
            }

            if (upgrade.getSkill().equals(Skill.PILOT)) {
                setPilot(pilot - upgrade.getBuff());
                buffToPilot -= upgrade.getBuff();
            }
        }
    }

    /**
     * Adds an upgrade to the list that the player owns
     * @param upgrade an upgrade to be added
     */
    public static void addUpgrade(Upgrade upgrade) {
        if (ownedUpgrades == null) {
            ownedUpgrades = new ArrayList<>();
        }
        if (!ownedUpgrades.contains(upgrade)) {
            ownedUpgrades.add(upgrade);
        }
    }

    public static void addItem(Good item) {
        if (ownedItems == null) {
            ownedItems = new ArrayList<Good>();
        }
        ownedItems.add(item);
    }

    public static void setHasWinningItem() {
        hasWinningItem = true;
    }

    public static boolean getHasWinningItem() {
        return hasWinningItem;
    }
}