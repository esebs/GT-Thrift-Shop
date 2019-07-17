package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.model.Model;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class PlayerViewModel {

    private final Model model = Model.getModelInstance();
    private final Player player = model.getPlayer();

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
        Room current = model.getRandomRoom();

        Player player = new Player(name, difficulty, current, pilotPoints, fighterPoints,
                traderPoints, engineerPoints);

        Log.d("APP", "PlayerViewModel: created player: \n" + player);

        model.setPlayer(player);

    }

    /**
     * Get the player's current Room
     * @return current Room
     */
    public Room getPlayerCurrentRoom() {
        return player.getCurrent();
    }
}
