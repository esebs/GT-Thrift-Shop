package com.github.esebs.cs2340project.spacetrader.viewmodels;

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

    /**
     * Brings the Player's Vehicle's range up to its max (charging the player to do so)
     */
    public void refillRange() {
        int maxRange = model.getPlayer().getVehicle().getMaxRange();
        model.getPlayer().getVehicle().setCurrentRange(maxRange);

        int credits = model.getPlayer().getCredits();
        // 1 unit of range costs 1 credit, so the price to purchase n units of range is n credits
        int price = calculateRangeUsed();
        model.getPlayer().setCredits(credits - price);
    }

}
