package com.github.esebs.cs2340project.spacetrader.viewmodels;

import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;

// --Commented out by Inspection START (7/16/2019 1:32 PM):
//class VehicleViewModel {
//
//    private final Model model = Model.getModelInstance();
//    private final Player player = model.getPlayer();
//    private final Vehicle vehicle = player.getVehicle();
//
//    /**
//     * Gets the relative price of this Vehicle compared to the player's current Vehicle
//     *
//     * @param vehicleForSale a Vehicle
//     * @return the price of this Vehicle
//     */
//    private int getVehiclePrice(Vehicle vehicleForSale) {
//        return vehicleForSale.getPrice() - vehicle.getPrice();
//    }
//
//    /**
//     * Determines whether the Player can afford this Vehicle
//     *
//     * @param vehicle a Vehicle
//     * @return whether the Player can purchase the Vehicle
//     */
//    public boolean canBuyVehicle(Vehicle vehicle) {
//        return getVehiclePrice(vehicle) >= 0;
//    }
//
//    /**
//     * Sets the player's current Vehicle to the passed in Vehicle.
//     * Also subtracts the cost of the new Vehicle from the Player's credits.
//     *
//     * @param vehicle the Vehicle to purchase
//     */
//    public void buyVehicle(Vehicle vehicle) {
//        player.setVehicle(vehicle);
//        int credits = player.getCredits();
//        player.setCredits(credits - getVehiclePrice(vehicle));
//    }
//}
// --Commented out by Inspection STOP (7/16/2019 1:32 PM)
