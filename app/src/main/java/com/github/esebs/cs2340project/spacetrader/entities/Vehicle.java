package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.Arrays;

/**
 * Enum with all possible vehicles, and there respective characteristics
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Vehicle {
    UNICYCLE("Unicycle", 10, 10, 25, 2000),
    SCOOTER("Scooter", 14, 15, 100, 10000),
    BIKE("Bike", 20, 20, 200, 25000),
    GOLFCART("Golf Cart", 25, 60, 400, 50000),
    MOPED("Moped", 50, 35, 350, 75000);

    private String vehicleType;
    private int fuelRange;
    private int cargoSize;
    private int[] cargoHold = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int maxHealth;
    private int currentHealth;
    private int price;

    /**
     * Creates the vehicle required
     * @param vehicleType name of vehicle type
     * @param fuelRange range of fuel
     * @param cargoSize size of cargo
     * @param maxHealth maximum value of currentHealth
     * @param price the vehicle's price
     */
    Vehicle(String vehicleType, int fuelRange, int cargoSize, int maxHealth, int price) {
        this.vehicleType = vehicleType;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.maxHealth = maxHealth;
        // currentHealth always starts at its maximum, which is determined by maxHealth
        this.currentHealth = maxHealth;
        this.price = price;
    }

    /**
     * Calculates the number of cargo space left in this Vehicle based on cargoHold's contents
     * @return number of remaining cargo space
     */
    public int calculateRemainingCargoSpace() {
        int spacesUsed = 0;
        for (int quantity : cargoHold) {
            spacesUsed += quantity;
        }
        return cargoSize - spacesUsed;
    }

    /**
     * Returns Vehicle's name
     * @return vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Returns the fuel range of the vehicle type
     * @return
     */
    public int getFuelRange() {
        return fuelRange;
    }

    /**
     * Sets the range of vehicle to a new range
     * @param fuelRange range of vehicle
     */
    public void setFuelRange(int fuelRange) {
        this.fuelRange = fuelRange;
    }

    /**
     * Returns the value for the size of cargo
     * @return cargoSize
     */
    public int getCargoSize() {
        return cargoSize;
    }

    /**
     * Sets cargo size to a new size
     * @param cargoSize new size of cargo
     */
    public void setCargoSize(int cargoSize) {
        this.cargoSize = cargoSize;
    }

    /**
     * Gets the player's cargo items
     * @return a Map of the player's cargo items
     */
    public int[] getCargoHold() {
        return cargoHold;
    }

    /**
     * Sets the player's cargo items
     * @param cargoHold a Map of the player's new cargo items
     */
    public void setCargoHold(int[] cargoHold) {
        this.cargoHold = cargoHold;
    }

    /**
     * Returns the max health of the vehicle
     * @return max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the max health of vehicle
     * @param maxHealth new max health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Returns vehicle's current health
     * @return current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the Vehicle's current health
     * @param currentHealth new health
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Gets the cost to purchase this vehicle
     * @return vehicle price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Creates String representation of Vehicle
     *
     * @return name of vehicle
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", fuelRange=" + fuelRange +
                ", cargoSize=" + cargoSize +
                ", cargoHold=" + Arrays.toString(cargoHold) +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", price=" + price +
                '}';
    }
}
