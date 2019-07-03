package com.github.esebs.cs2340project.spacetrader.viewmodels;

import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;

public class VehicleViewModel {

    private Model model = Model.getModelInstance();

    /**
     * Gets the relative price of this Vehicle compared to the player's current Vehicle
     *
     * @param vehicle a Vehicle
     * @return the price of this Vehicle
     */
    public int getVehiclePrice(Vehicle vehicle) {
        Vehicle playerVehicle = model.getPlayer().getVehicle();

        return vehicle.getPrice() - playerVehicle.getPrice();
    }

    /**
     * Determines whether the Player can afford this Vehicle
     *
     * @param vehicle a Vehicle
     * @return whether the Player can purchase the Vehicle
     */
    public boolean canBuyVehicle(Vehicle vehicle) {
        return getVehiclePrice(vehicle) >= 0;
    }

    /**
     * Sets the player's current Vehicle to the passed in Vehicle.
     * Also subtracts the cost of the new Vehicle from the Player's credits.
     *
     * @param vehicle the Vehicle to purchase
     */
    public void buyVehicle(Vehicle vehicle) {
        model.getPlayer().setVehicle(vehicle);
        int credits = model.getPlayer().getCredits();
        model.getPlayer().setCredits(credits - getVehiclePrice(vehicle));
    }
}
