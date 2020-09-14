package objects;
import data.Good;
import data.ShipModel;
import javafx.stage.Stage;
import scenes.GameOver;

import java.util.ArrayList;

/**
 * class representing a ship that a player can use
 * @author Andrew Harris
 * @version 1.0
 */
public class Ship {
    private String name;
    private int cargoCapacity;
    private ArrayList<Good> cargoList;
    private int currentFuel;
    private int fuelCapacity;
    private int currentHealth;
    private int maxHealth;
    private final ShipModel model;
    private Stage primaryStage;

    /**
     * constructor for a Ship object
     * @param type a ShipModel enum
     */
    public Ship(ShipModel type, Stage primaryStage) {
        this.name = type.getName();
        this.cargoCapacity = type.getCargoCapacity();
        this.currentFuel = type.getFuelCapacity();
        this.fuelCapacity = type.getFuelCapacity();
        this.currentHealth = type.getMaxHealth();
        this.maxHealth = type.getMaxHealth();
        this.model = type;
        this.cargoList = new ArrayList<>(this.cargoCapacity);
        this.primaryStage = primaryStage;

    }

    public String getName() {
        return name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getContainsCargoNum(Good good) {
        int i = 0;
        for (Good good2 : cargoList) {
            if (good == good2) {
                i++;
            }
        }
        return i;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public ShipModel getModel() {
        return model;
    }

    public ArrayList getCargo() {
        return cargoList;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void addCargo(Good good) {
        cargoList.add(good);
    }

    public void addCargo(Good good, int num) {
        for (int i = 0; i < num; i++) {
            cargoList.add(good);
        }
    }

    public void removeCargo(Good good) {
        cargoList.remove(good);
    }

    public void removeCargo(Good good, int num) {
        for (int i = 0; i < num; i++) {
            cargoList.remove(good);
        }
    }

    public int getCargoSize() {
        return cargoList.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentFuel(int val) {
        this.currentFuel = val;
    }

    public void setCurrentHealth(int val) {
        this.currentHealth = val;

        if (this.currentHealth <= 0) {
            System.out.println("Should have lost the game");
            primaryStage.setScene(new GameOver(primaryStage).get());
        }
    }
}