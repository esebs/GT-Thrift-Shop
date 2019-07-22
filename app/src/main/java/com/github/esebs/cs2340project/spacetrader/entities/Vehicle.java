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
    GOLF_CART("Golf Cart", 25, 60, 400, 50000),
    MOPED("Moped", 50, 35, 350, 75000);

    private final String vehicleType;
    private final int maxRange;

    private final int cargoSize;
    private final int maxHealth;
    private int currentHealth;
    private final int price;

    /**
     * Creates the vehicle required
     * @param vehicleType name of vehicle type
     * @param maxRange maximum travel range
     * @param cargoSize size of cargo
     * @param maxHealth maximum vehicle health
     * @param price the vehicle's price
     */
    Vehicle(String vehicleType, int maxRange, int cargoSize, int maxHealth, int price) {
        this.vehicleType = vehicleType;
        this.maxRange = maxRange;
        // currentRange always starts at its maximum, which is determined by maxRange
        this.cargoSize = cargoSize;
        this.maxHealth = maxHealth;
        // currentHealth always starts at its maximum, which is determined by maxHealth
        this.currentHealth = maxHealth;
        this.price = price;

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
     * @return maximum range of this vehicle
     */
    public int getMaxRange() {
        return maxRange;
    }

    /**
     * Returns the value for the size of cargo
     * @return cargoSize
     */
    public int getCargoSize() {
        return cargoSize;
    }


    /**
//     * Returns the max health of the vehicle
//     * @return max health
//     */
    public int getMaxHealth() {
        return maxHealth;
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
        this.currentHealth = currentHealth > 0 ? currentHealth : 0;
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
                ", maxRange=" + maxRange +
                ", cargoSize=" + cargoSize +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", price=" + price +
                '}';
    }
}
