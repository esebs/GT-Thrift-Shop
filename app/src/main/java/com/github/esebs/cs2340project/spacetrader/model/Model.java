package com.github.esebs.cs2340project.spacetrader.model;

import com.github.esebs.cs2340project.spacetrader.entities.Player;

public class Model {

    // The player of the game
    private Player player;

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
}
