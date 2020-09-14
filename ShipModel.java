package data;

import javafx.scene.image.Image;

/**
 * enum representing ship models
 * @author Andrew Harris
 * @version 1.0
 */
public enum ShipModel {
    BIG_CHUNGUS("Big Chungus", 10, 50, 50);

    private String name;
    private int cargoCapacity;
    private int fuelCapacity;
    private int maxHealth;
    private final Image shipImage = new Image("images/bigChungus.jpg");

    /**
     * constructor
     * @param name name of the model
     * @param cargoCapacity default cargo capacity of the model
     * @param fuelCapacity default fuel capacity of the model
     * @param maxHealth default max health of the model
     */
    ShipModel(String name, int cargoCapacity, int fuelCapacity, int maxHealth) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
        this.maxHealth = maxHealth;
    }

    /**
     * getter for model name
     * @return model name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for default cargo capacity
     * @return cargo capacity
     */
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * getter for default fuel capacity
     * @return fuel capacity
     */
    public int getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * getter for default max health
     * @return max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    public Image getShipImage() {
        return shipImage;
    }


}
