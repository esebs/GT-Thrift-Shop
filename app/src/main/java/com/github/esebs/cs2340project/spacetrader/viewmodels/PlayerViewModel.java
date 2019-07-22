package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.model.Model;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class PlayerViewModel {

    private final Model model;
    private final Player player;

    /**
     * Constructor for PlayerViewModel
     */
    public PlayerViewModel() {
        model = Model.getModelInstance();
        player = model.getPlayer();
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
     *
     * @param name Player's name
     * @param difficulty easy, medium, hard, Bob Waters
     * @param pilotPoints Piloting Skill Points
     * @param fighterPoints Fighting Skill Points
     * @param traderPoints Trader Skill Points
     * @param engineerPoints Engineering Skill Points
     */
    public void setPlayer(String name, Difficulty difficulty, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints) {
        Building building = model.getRandomBuilding();
        Room current = building.getRooms().get(0);

        Player player = new Player(name, difficulty, current, building, pilotPoints, fighterPoints,
                traderPoints, engineerPoints);

        Log.d("APP", "PlayerViewModel: created player: \n" + player);

        model.setPlayer(player);
    }

    /**
     *
     * @param player new player
     */
    public void setPlayer(Player player) {
        model.setPlayer(player);
    }

    /**
     * Does magic
     * @param cargoHold new cargohold
     */
    public void setCargoHold(int[] cargoHold) {
        model.getPlayer().setCargoHold(cargoHold);
    }

    /**
     * Get the player's current Room
     * @return current Room
     */
    public Room getPlayerCurrentRoom() {
        return player.getCurrent();
    }
    public void setLoaded(boolean loaded) {
        model.setLoaded(loaded);
    }

    public boolean isLoaded() {
        return model.isLoaded();
    }
}
