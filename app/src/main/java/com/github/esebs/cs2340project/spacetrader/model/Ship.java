package com.github.esebs.cs2340project.spacetrader.model;

public enum Ship {
    GNAT("Gnat", 14, 15, 100, 100);

    private String shipName;
    private int fuelRange;
    private int cargoSize;
    private int hullStrength;
    private int health;

    Ship(String shipName, int fuelRange, int cargoSize, int hullStrength, int health) {
        this.shipName = shipName;
        this.fuelRange = fuelRange;
        this.fuelRange = fuelRange;
        this.cargoSize = cargoSize;
        this.hullStrength = hullStrength;
        this.health = health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return shipName;
    }
}
