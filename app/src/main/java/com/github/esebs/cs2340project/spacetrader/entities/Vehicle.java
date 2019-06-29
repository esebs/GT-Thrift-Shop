package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum with all possible vehicles, and there respective characteristics
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Vehicle {
    GNAT("Gnat", 14, 15, 100);

    private String vehicleType;
    private int fuelRange;
    private int cargoSize;
    private Map<Resource, Integer> cargoHold = new HashMap<>();
    private int hullStrength;
    private int currentHealth;

    /**
     * Creates the vehicle required
     * @param vehicleType name of vehicle type
     * @param fuelRange range of fuel
     * @param cargoSize size of cargo
     * @param hullStrength maximum value of currentHealth
     */
    Vehicle(String vehicleType, int fuelRange, int cargoSize, int hullStrength) {
        this.vehicleType = vehicleType;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.hullStrength = hullStrength;
        // currentHealth always starts at its maximum, which is determined by hullStrength
        this.currentHealth = hullStrength;
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
    public Map<Resource, Integer> getCargoHold() {
        return cargoHold;
    }

    /**
     * Sets the player's cargo items
     * @param cargoHold a Map of the player's new cargo items
     */
    public void setCargoHold(Map<Resource, Integer> cargoHold) {
        this.cargoHold = cargoHold;
    }

    /**
     * Returns the max health of the vehicle
     * @return max health
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * Sets the max health of vehicle
     * @param hullStrength new max health
     */
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
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
     * Creates String representation of Vehicle
     * @return name of vehicle
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "VehicleType='" + vehicleType + '\'' +
                ", fuelRange=" + fuelRange +
                ", cargoSize=" + cargoSize +
                ", cargoHold=" + cargoHold +
                ", hullStrength=" + hullStrength +
                ", currentHealth=" + currentHealth +
                '}';
    }
}
