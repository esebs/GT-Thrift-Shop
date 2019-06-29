package com.github.esebs.cs2340project.spacetrader.model;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * Sets the game's buildings
     * @param buildings a List of the universe's buildings
     */
    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
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

    /**
     * Returns a random room, used to initialize player
     * @return random room
     */
    public Room getRandomRoom() {
        Random rn = new Random();
        int random = rn.nextInt(getBuildings().size());
        return getBuildings().get(random).getRooms().get(0);
    }
}
