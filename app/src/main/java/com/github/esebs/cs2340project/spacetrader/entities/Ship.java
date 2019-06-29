package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enum with all possible ships, and there respective characteristics
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Ship {
    GNAT("Gnat", 14, 15, 100);

    private String shipType;
    private int fuelRange;
    private int cargoSize;
    // 10 different resources
    // private Map<Resource, Integer> cargoHold = new HashMap<>(10);
    private int hullStrength;
    private int currentHealth;

    /**
     * Creates the ship required
     * @param shipType name of ship type
     * @param fuelRange range of fuel
     * @param cargoSize size of cargo
     * @param hullStrength maximum value of currentHealth
     */
    Ship(String shipType, int fuelRange, int cargoSize, int hullStrength) {
        this.shipType = shipType;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.hullStrength = hullStrength;
        // currentHealth always starts at its maximum, which is determined by hullStrength
        this.currentHealth = hullStrength;
    }

    /**
     * Returns Ship's name
     * @return shipType
     */
    public String getshipType() {
        return shipType;
    }

    /**
     * Returns the fuel range of the ship type
     * @return
     */
    public int getFuelRange() {
        return fuelRange;
    }

    /**
     * Sets the range of ship to a new range
     * @param fuelRange range of ship
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

//    /**
//     * Gets the player's cargo items
//     * @return a Map of the player's cargo items
//     */
//    public Map<Resource, Integer> getCargoHold() {
//        return cargoHold;
//    }
//
//    /**
//     * Sets the player's cargo items
//     * @param cargoHold a Map of the player's new cargo items
//     */
//    public void setCargoHold(Map<Resource, Integer> cargoHold) {
//        this.cargoHold = cargoHold;
//    }

    /**
     * Returns the max health of the ship
     * @return max health
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * Sets the max health of ship
     * @param hullStrength new max health
     */
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    /**
     * Returns ship's current health
     * @return current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the ship's current health
     * @param currentHealth new health
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Creates String representation of Ship
     * @return name of ship
     */
    @Override
    public String toString() {
        return shipType;
    }
}
