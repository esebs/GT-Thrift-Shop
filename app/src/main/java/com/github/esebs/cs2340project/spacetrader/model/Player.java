package com.github.esebs.cs2340project.spacetrader.model;

public class Player {
    private String name;
    private Difficulty difficulty;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private final int MAX_POINTS = 20;

    public Player(String name, Difficulty difficulty, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints) {
        this.name = name;
        this.difficulty = difficulty;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.credits = 1000;
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public int getMAX_POINTS() {
        return MAX_POINTS;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Difficulty: " + difficulty
                + "; Pilot: " + pilotPoints + "; Fighter: "
                + fighterPoints + "; Trader: " + traderPoints
                + "; Engineer: " + engineerPoints;
    }
}
