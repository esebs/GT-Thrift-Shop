package com.github.esebs.cs2340project.spacetrader.entities;
import java.util.Arrays;

/**
 * Creates player class
 * @version 1.0
 * @author Sebastian Escobar
 */
public class Player {
    private final String name;
    private final Difficulty difficulty;
    private Room current;
    private Building currentBuilding;
    private Vehicle vehicle;
    private final int pilotPoints;
    private final int fighterPoints;
    private final int traderPoints;
    private final int engineerPoints;
    private int credits;
    private int[] cargoHold;
    private int currentRange;

    /**
     * Creates a new instance of a player
     * @param name Player's name
     * @param difficulty easy, medium, hard, Bob Waters
     * @param current the starting Room
     * @param pilotPoints Piloting Skill Points
     * @param fighterPoints Fighting Skill Points
     * @param traderPoints Trader Skill Points
     * @param engineerPoints Engineering Skill Points
     */
    public Player(String name, Difficulty difficulty, Room current, Building currentBuilding, int pilotPoints,
                  int fighterPoints, int traderPoints, int engineerPoints) {
        this(name, difficulty, current, currentBuilding, pilotPoints, fighterPoints, traderPoints,engineerPoints, new int[10], Vehicle.SCOOTER.getMaxRange());
        this.currentRange = vehicle.getMaxRange();
    }

    public Player(String name, Difficulty difficulty, Room current, Building currentBuilding, int pilotPoints,
                  int fighterPoints, int traderPoints, int engineerPoints, int[] cargoHold, int currentRange) {
        this.name = name;
        this.difficulty = difficulty;
        this.current = current;
        this.currentBuilding = currentBuilding;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.credits = 1000;
        this.vehicle = Vehicle.SCOOTER;
        this.cargoHold = cargoHold;
        this.currentRange = currentRange;
    }

//    /**
//     * Returns player's name
//     * @return name
//     */
//    public String getName() {
//        return name;
//    }

    /**
     * Returns current difficulty
     * @return difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the Vehicle's current range of travel
     * @return current range
     */
    public int getCurrentRange() {
        return currentRange;
    }

    /**
     * Sets the Vehicle's current range of travel
     * @param currentRange new current range
     */
    public void setCurrentRange(int currentRange) {
        this.currentRange = currentRange;
    }


    /**
     * Gets the Player's current location
     * @return Player's current Room
     */
    public Room getCurrent() {
        return current;
    }

    /**
     * Sets the Player's current location
     * @param current The Player's new Room
     */
    public void setCurrent(Room current) {
        this.current = current;
    }

    /**
     * Gets the vehicle
     * @return vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets vehicle to a new value
     * @param vehicle new vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

//    /**
//     * Returns pilot points
//     * @return
//     */
//    public int getPilotPoints() {
//        return pilotPoints;
//    }
//
//    /**
//     * Sets Pilot points to a new value
//     * @param pilotPoints new Pilot points
//     */
//    public void setPilotPoints(int pilotPoints) {
//        this.pilotPoints = pilotPoints;
//    }
//
    /**
     * Returns player's fighter points
     * @return fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }
//
//    /**
//     * Sets fighter points to a new value
//     * @param fighterPoints
//     */
//    public void setFighterPoints(int fighterPoints) {
//        this.fighterPoints = fighterPoints;
//    }
//
    /**
     * Returns player's trader points
     * @return
     */
    public int getTraderPoints() {
        return traderPoints;
    }
//
//    /**
//     * Sets trader points to a new value
//     * @param traderPoints new trader point value
//     */
//    public void setTraderPoints(int traderPoints) {
//        this.traderPoints = traderPoints;
//    }
//
//    /**
//     * Returns player's engineer points
//     * @return engineerPoints
//     */
//    public int getEngineerPoints() {
//        return engineerPoints;
//    }
//
//    /**
//     * Sets player's engineer points to new value
//     * @param engineerPoints new engineer points
//     */
//    public void setEngineerPoints(int engineerPoints) {
//        this.engineerPoints = engineerPoints;
//    }

    /**
     * Returns current credits
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * sets credits to new value
     * @param credits new value for credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * gets the building
     */
    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    /**
     * sets it to a new building
     * @param currentBuilding new building
     */
    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
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
        return vehicle.getCargoSize() - spacesUsed;
    }

    /**
     * Gets the player's cargo items
     * @return a Map of the player's cargo items
     */
    public int[] getCargoHold() {
        return cargoHold.clone();
    }

    /**
     * Sets the player's cargo items
     * @param cargoHold a Map of the player's new cargo items
     */
    public void setCargoHold(int[] cargoHold) {
        this.cargoHold = cargoHold.clone();
    }
    /**
     * Attacks an encountered vehicle
     *
     * @param encounter encountered vehicle
     * @return True if the player attacks the vehicle, False if the player misses
     */
    public Boolean attack(Encounterable encounter) {
        int damage = 10;
        boolean attacks;

        final double missMultiplier = 0.85;
        double missingChance = missMultiplier / fighterPoints;

        double randomChance = Math.random();

        if (randomChance > missingChance) {
            attacks = true;
            int encounterHealth = encounter.getHealth();
            encounter.setHealth(encounterHealth - damage);
        } else {
            attacks = false;
        }
        return attacks;
    }

    /**
     * Flees from an encountered vehicle (only Police or Pirates, not Traders)
     *
     * @param encounter encountered vehicle
     * @return True if the player successfully flees, False if the player
     * doesn't flee
     */
    public Boolean flee(Encounterable encounter) {
        final double fleeChance = (5 * pilotPoints) * 0.01;

        double randomChance = Math.random();
        return !(randomChance > fleeChance);
    }

    /**
     * Trades with a Trader instance. The player can only buy a certain
     * quantity if the trader has at least that quantity, if the player
     * can afford to buy that quantity, and if the player's cargo hold
     * has space for that quantity.
     *
     * @param trader the Trader to trade with
     * @param quantity the amount of the Trader's resource to buy
     */
    public void trade(Trader trader, int quantity) {
        if (quantity > trader.calculateMaxBuyQuantity()) {
            return;
        }
        int cost = quantity * trader.getPrice();
        credits -= cost;

        int[] playerItems = this.getCargoHold();
        int resourceIndex = trader.getResource().ordinal();
        playerItems[resourceIndex] += quantity;
        this.setCargoHold(playerItems);
    }

    /**
     * Returns whether the player has died
     *
     * @return health of player's vehicle
     */
    public boolean isDead() {
        return vehicle.getCurrentHealth() <= 0;
    }

    /**
     * toString method for Player
     *
     * @return string representation of this Player
     */
    @Override
    public String toString() {
        return "Player {" +
                "\n\tname: '" + name + '\'' +
                "\n\tdifficulty:" + difficulty +
                "\n\tvehicle: " + vehicle.getVehicleType() +
                "\n\tRoom: " + current.getName() +
                "\n\tpilotPoints: " + pilotPoints +
                "\n\tfighterPoints: " + fighterPoints +
                "\n\ttraderPoints: " + traderPoints +
                "\n\tengineerPoints: " + engineerPoints +
                "\n\tcredits: " + credits +
                ", cargoHold=" + Arrays.toString(cargoHold) +
                "\n }";
    }
}
