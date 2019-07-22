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
        this(name, difficulty, current, currentBuilding, pilotPoints, fighterPoints, traderPoints,engineerPoints, new int[10]);
    }

    public Player(String name, Difficulty difficulty, Room current, Building currentBuilding, int pilotPoints,
                  int fighterPoints, int traderPoints, int engineerPoints, int[] cargoHold) {
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
    }

//    /**
//     * Returns player's name
//     * @return name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * sets name to new variable
//     * @param name player's new name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * Returns current difficulty
//     * @return difficulty
//     */
//    public Difficulty getDifficulty() {
//        return difficulty;
//    }
//
//    /**
//     * Sets new difficulty
//     * @param difficulty enum difficulty
//     */
//    public void setDifficulty(Difficulty difficulty) {
//        this.difficulty = difficulty;
//    }

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
//    /**
//     * Returns player's fighter points
//     * @return fighterPoints
//     */
//    public int getFighterPoints() {
//        return fighterPoints;
//    }
//
//    /**
//     * Sets fighter points to a new value
//     * @param fighterPoints
//     */
//    public void setFighterPoints(int fighterPoints) {
//        this.fighterPoints = fighterPoints;
//    }
//
//    /**
//     * Returns player's trader points
//     * @return
//     */
//    public int getTraderPoints() {
//        return traderPoints;
//    }
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
