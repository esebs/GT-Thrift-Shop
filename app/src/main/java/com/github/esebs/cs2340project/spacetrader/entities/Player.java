package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.Random;

import static java.lang.Math.random;

/**
 * Creates player class
 * @version 1.0
 * @author Sebastian Escobar
 */
public class Player {
    private final String name;
    private final Difficulty difficulty;
    private Room current;
    private Vehicle vehicle;
    private final int pilotPoints;
    private final int fighterPoints;
    private final int traderPoints;
    private final int engineerPoints;
    private int credits;

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
    public Player(String name, Difficulty difficulty, Room current, int pilotPoints,
                  int fighterPoints, int traderPoints, int engineerPoints) {
        this.name = name;
        this.difficulty = difficulty;
        this.current = current;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.credits = 1000;
        this.vehicle = Vehicle.SCOOTER;
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
     * Attacks an encountered vehicle
     *
     * @param encounter encountered vehicle
     * @return True if the player attacks the vehicle, False if the player misses
     */
    public Boolean attack(Encounterable encounter) {
        int damage = 10;
        boolean attacks;

        final double missMultiplier = 0.85;
        double missingChance = missMultiplier * fighterPoints;

        double randomChance = Math.random();

        if (randomChance > missingChance) {
            attacks = true;
            Vehicle encounterVehicle = encounter.getVehicle();
            int currentHealth = encounterVehicle.getCurrentHealth();
            encounterVehicle.setCurrentHealth(currentHealth - damage);
        } else {
            attacks = false;
        }
        return attacks;
    }

    /**
     * Flees from an encountered vehicle
     *
     * @param encounter encountered vehicle
     * @return True if the player successfully flees, False if the player
     * doesn't flee and is attacked
     */
    public Boolean flee(Encounterable encounter) {
        boolean flees;
        final double fleeChance = (5 * pilotPoints) * 0.01;

        double randomChance = Math.random();

        if (randomChance > fleeChance) {
            flees = false;
            encounter.attack();
        } else {
            flees = true;
        }
        return flees;
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
                "\n }";
    }
}
