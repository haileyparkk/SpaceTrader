package data;

/**
 * enum representing all goods in the game
 * @author Joseph Lukemire, Andrew Harris
 */
public enum Good {
    WATER("Water", 134.50, 1),
    FUR("Fur", 150.00, 2),
    FOOD("Food", 234.50, 1),
    ORE("Ore", 172.50, 3),
    GAME("Game", 233.00, 5),
    FIREARM("Firearm", 203.00, 5),
    MEDICINE("Medicine", 180.00, 3),
    MACHINES("Machine", 150.00, 7),
    NARCOTICS("Illegal Narcotics", 50.00, 1),
    FUEL("Fuel", 225.00, 1),
    FLATULENCE("Flatulence", 113.0, 1),
    HAIRGEL("Hair Gel", 126.0, 4),
    MAYONNAISE("Mayonnaise", 132.2, 1),
    CLOTHING("Clothing", 152.0, 1),
    SEEDS("Seeds", 142.5, 1),
    RUBBER("Rubber", 162.3, 1),
    ANTIQIUES("Antiques", 149.1, 1),
    FOSSIL("Fossil", 160.3, 1),
    GLASS("Glass", 143.4, 1),
    SPRINGS("Springs", 147.6, 1),
    PAINTINGS("Paintings", 140.2, 1),
    WOOD("Wood", 230.3, 1);

    /**
     * getter for a good's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the good's price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter for the minimum tech level of an item
     * @return tech level of the good
     */
    public int getMinTechLevel() {
        return minTechLevel;
    }

    private String name;
    private double price;
    private int minTechLevel;

    /**
     * constructor for a good
     * @param name the name
     * @param price the price
     * @param minTechLevel the tech level
     */
    Good(String name, double price, int minTechLevel) {
        this.name = name;
        this.price = price;
        this.minTechLevel = minTechLevel;
    }
}
