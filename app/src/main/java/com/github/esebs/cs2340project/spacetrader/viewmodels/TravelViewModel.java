package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.model.Model;

public class TravelViewModel {

    private Model model = Model.getModelInstance();

    /**
     * Calculates the difference between the maximum range and current range of the Player's Vehicle
     * @return how much range has been used up
     */
    private int calculateRangeUsed() {
        int maxRange = model.getPlayer().getVehicle().getMaxRange();
        int currentRange = model.getPlayer().getVehicle().getCurrentRange();
        return maxRange - currentRange;
    }

    public int getCredits() {
        return model.getPlayer().getCredits();
    }

    /**
     * Gets the Player's Vehicle's remaining range
     * @return remaining range
     */
    public int getCurrentRange() {
        return model.getPlayer().getVehicle().getCurrentRange();
    }

    /**
     * Returns true of the Player's Vehicle's current range is equal to its maximum range.
     * False otherwise
     * @return if the range is at it's max
     */
    public boolean isRangeMax() {
        return calculateRangeUsed() == 0;
    }

    public int getCostToRefuel() {
        return this.calculateRangeUsed() * 5;
    }

    /**
     * Brings the Player's Vehicle's range up to its max (charging the player to do so)
     */
    public void refillRange() {
        int maxRange = model.getPlayer().getVehicle().getMaxRange();
        model.getPlayer().getVehicle().setCurrentRange(maxRange);

        int credits = model.getPlayer().getCredits();
        int price = getCostToRefuel();
        model.getPlayer().setCredits(credits - price);
    }

    public void travelTo(Room newRoom) {
        model.getPlayer().setCurrent(newRoom);
        model.getPlayer().getVehicle().setCurrentRange(
                (model.getPlayer().getVehicle().getCurrentRange() - 5));
        Log.d("APP", "TravelViewModel: Player Travelled to: "
                + model.getPlayer().getCurrent() + ". You have "
                + model.getPlayer().getVehicle().getCurrentRange() + "fuel left.");
    }

}
