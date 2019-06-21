package com.github.esebs.cs2340project.spacetrader.model;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author Travis Adams
 */
public class Model {

    // The player of the game
    private Player player;
    private List<Building> buildings = new ArrayList<>();

    // This class is only instantiated ONCE
    private static Model modelInstance = new Model();

    /**
     * Returns the single, static Model instance
     *
     * @return the static Model instance
     */
    public static Model getModelInstance() {
        return modelInstance;
    }

    /**
     * Makes a new Model instance
     */
    private Model() {
        // Instantiate Buildings

        // Create CULC
        Building culc = new Building("Clough Undergraduate Learning Commons",
                69, 69, Arrays.asList("152", "244"));
        buildings.add(culc);

        // Create Klaus
        Building klaus = new Building("Klaus Advanced Computing Building",
                69, 69, Arrays.asList("1443", "2443"));
        buildings.add(klaus);

        // Create Scheller
        Building scheller = new Building("Scheller College of Business",
                69, 69, Arrays.asList("254", "432"));
        buildings.add(scheller);

        // Create Mason
        Building mason = new Building("Mason Building",
                69, 69, Arrays.asList("101", "23"));
        buildings.add(mason);

        // Create Instructional Center
        Building ic = new Building("Instructional Center",
                69, 69, Arrays.asList("205", "105"));
        buildings.add(ic);

        // Create Skiles
        Building skiles = new Building("Skiles Classroom Building",
                69, 69, Arrays.asList("314", "317"));
        buildings.add(skiles);

        // Create CRC
        Building crc = new Building("Campus Recreation Center",
                69, 69, Arrays.asList("5", "110"));
        buildings.add(crc);

        // Create Architecture
        Building arch = new Building("Architecture Building",
                69, 69, Arrays.asList("123", "201"));
        buildings.add(arch);

        // Create Student Center
        Building sc = new Building("Student Center",
                69, 69, Arrays.asList("Piedmont", "69"));
        buildings.add(sc);

        // Create Ford ES&T
        Building ford = new Building("Ford ES&T Building",
                69, 69, Arrays.asList("341", "122"));
        buildings.add(ford);
    }

    /**
     * Gets the player for the game
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player for the game
     * @param player instance of Player class
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the buildings for the game
     * @return the buildings
     */
    public List<Building> getBuildings() {
        return buildings;
    }

    /**
     * toString method for Model
     *
     * @return string representation of the Model
     */
    @Override
    public String toString() {
        return "Model{" +
                "buildings=" + buildings +
                '}';
    }
}
