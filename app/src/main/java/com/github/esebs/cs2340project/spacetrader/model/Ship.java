package com.github.esebs.cs2340project.spacetrader.model;

/**
 * Enum with all possible ships, and there respective characteristics
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Ship {
    GNAT("Gnat", 14, 15, 100, 100);

    private String shipName;
    private int fuelRange;
    private int cargoSize;
    private int hullStrength;
    private int health;

    /**
     * Creates the ship required
     * @param shipName name of ship type
     * @param fuelRange range of fuel
     * @param cargoSize size of cargo
     * @param hullStrength maximum value of health
     * @param health current health
     */
    Ship(String shipName, int fuelRange, int cargoSize, int hullStrength, int health) {
        this.shipName = shipName;
        this.fuelRange = fuelRange;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.hullStrength = hullStrength;
        this.health = health;
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
        return shipName;
    }
}
