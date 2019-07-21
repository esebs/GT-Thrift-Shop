package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;

/**
 * ViewModel for travel
 */
public class TravelViewModel {

    private final Model model = Model.getModelInstance();
    private final Player player = model.getPlayer();
    private final Room currentRoom = player.getCurrent();
    private final Vehicle vehicle = player.getVehicle();

    /**
     * Calculates the difference between the maximum range and current range of the Player's Vehicle
     * @return how much range has been used up
     */
    private int calculateRangeUsed() {
        int maxRange = vehicle.getMaxRange();
        int currentRange = vehicle.getCurrentRange();
        return maxRange - currentRange;
    }

    /**
     * Gets the Player's number of credits
     * @return number of credits
     */
    public int getCredits() {
        return player.getCredits();
    }

    /**
     * Gets the Player's Vehicle's remaining range
     * @return remaining range
     */
    public int getCurrentRange() {
        return vehicle.getCurrentRange();
    }

    /**
     * Returns true of the Player's Vehicle's current range is equal to its maximum range.
     * False otherwise
     * @return if the range is at it's max
     */
    public boolean isRangeMax() {
        return calculateRangeUsed() == 0;
    }

    /**
     * Get how many credits are required to completely refill the vehicle's range
     * @return cost to refuel vehicle
     */
    public int getCostToRefuel() {
        return this.calculateRangeUsed() * 5;
    }

    /**
     * Brings the Player's Vehicle's range up to its max (charging the player to do so)
     */
    public void refillRange() {
        int maxRange = vehicle.getMaxRange();

        player.setCredits(getCredits() - this.getCostToRefuel());
        
        vehicle.setCurrentRange(maxRange);
        Log.d("APP", "TravelViewModel: Player's range: "
                + vehicle.getCurrentRange() + ". Credits remaining "
                + getCredits() + " cr.");

    }

    /**
     * Moves the player to the new room, subtracts the distance traveled from the player's
     * vehicle's range
     * @param newRoom player's new room
     */
    public void travelTo(Room newRoom, Building building) {
        player.setCurrent(newRoom);
        player.setCurrentBuilding(building);
        vehicle.setCurrentRange(
                (vehicle.getCurrentRange() - 5));
        Log.d("APP", "TravelViewModel: Player Travelled to: "
                + currentRoom.getName() + ". You have "
                + vehicle.getCurrentRange() + "fuel left.");

    }

}
