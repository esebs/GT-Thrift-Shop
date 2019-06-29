package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class PlayerViewModel {

    private Model model = Model.getModelInstance();

    /**
     * Gets the player for the game
     * @return the player
     */
    public Player getPlayer() {
        return Model.getModelInstance().getPlayer();
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

        Player player = new Player(name, difficulty, pilotPoints, fighterPoints, traderPoints,
                engineerPoints);

        // Instantiate the vehicle's cargo hold (0 of each resource)
        player.getVehicle().setCargoHold(instantiateCargoHold());

        Log.d("APP", "PlayerViewModel: created player: " + player);

        model.setPlayer(player);

    }

    /**
     * Creates an empty cargo hold
     * @return a map of the possible resources, each with a value (quantity) of 0
     */
    private Map<Resource, Integer> instantiateCargoHold() {
        Map<Resource, Integer> cargoHold = new HashMap<>();
        for (Resource resource : Resource.values()) {
            cargoHold.put(resource, 0);
        }
        return cargoHold;
    }
}
