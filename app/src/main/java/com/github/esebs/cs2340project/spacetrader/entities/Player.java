package com.github.esebs.cs2340project.spacetrader.entities;
/**
 * Creates player class
 * @version 1.0
 * @author Sebastian Escobar
 */
public class Player {
    private String name;
    private Difficulty difficulty;
    private Ship ship;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private final int MAX_POINTS = 20;

    /**
     * Creates a new instance of a player
     * @param name Player's name
     * @param difficulty easy, medium, hard, Bob Waters
     * @param pilotPoints Piloting Skill Points
     * @param fighterPoints Fighting Skill Points
     * @param traderPoints Trader Skill Points
     * @param engineerPoints Engineering Skill Points
     */
    public Player(String name, Difficulty difficulty, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints) {
        this.name = name;
        this.difficulty = difficulty;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.credits = 1000;
        this.ship = Ship.GNAT;
    }

    /**
     * Returns player's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name to new variable
     * @param name player's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return's current difficulty
     * @return difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets new difficulty
     * @param difficulty enum difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the ship
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets ship to a new value
     * @param ship new ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Returns pilot points
     * @return
     */
    public int getPilotPoints() {
        return pilotPoints;
    }

    /**
     * Sets Pilot points to a new value
     * @param pilotPoints new Pilot points
     */
    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * Returns player's fighter points
     * @return fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }

    /**
     * Sets fighter points to a new value
     * @param fighterPoints
     */
    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * Returns player's trader points
     * @return
     */
    public int getTraderPoints() {
        return traderPoints;
    }

    /**
     * Sets trader points to a new value
     * @param traderPoints new trader point value
     */
    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    /**
     * Returns player's engineer points
     * @return engineerPoints
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     * Sets player's engineer points to new value
     * @param engineerPoints new engineer points
     */
    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

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
     * gets the max value for Skill Points
     * @return maximum skill points
     */
    public int getMAX_POINTS() {
        return MAX_POINTS;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Difficulty: " + difficulty + "; Ship: "
                + ship + "; Pilot: " + pilotPoints + "; Fighter: "
                + fighterPoints + "; Trader: " + traderPoints
                + "; Engineer: " + engineerPoints;
    }
}
