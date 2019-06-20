package com.github.esebs.cs2340project.spacetrader.model;

/**
 * Enum with all possible ships, and there respective characteristics
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Ship {
    GNAT("Gnat", 14, 15, 100, 100);

    private String shipType;
    private int fuelRange;
    private int cargoSize;
    private int hullStrength;
    private int health;

    /**
     * Creates the ship required
     * @param shipType name of ship type
     * @param fuelRange range of fuel
     * @param cargoSize size of cargo
     * @param hullStrength maximum value of health
     * @param health current health
     */
    Ship(String shipType, int fuelRange, int cargoSize, int hullStrength, int health) {
        this.shipType = shipType;
        this.fuelRange = fuelRange;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.hullStrength = hullStrength;
        this.health = health;
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

    /**
     * Returns the max health of the ship
     * @return max health
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * Sets the max health of ship to a new value
     * @param hullStrength new max health
     */
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    /**
     * returns ship's health
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * sets health to new health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
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
