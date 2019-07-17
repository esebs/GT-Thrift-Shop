package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;

/**
 * ViewModel for trading
 */
public class TradingViewModel {

    private final Model model = Model.getModelInstance();
    private final Player player = model.getPlayer();
    private final Room currentRoom = player.getCurrent();
    private final Vehicle vehicle = player.getVehicle();

    /**
     * Gets how many of a resource is offered by the Player's current Room
     *
     * @param resource a Resource
     * @return the quantity of the resource offered
     */
    public int getBuyQuantity(Resource resource) {
        return currentRoom.getBuyFromRoomQuantities()[resource.ordinal()];
    }

    /**
     * Gets how many of a resource are in the Player's cargoHold
     *
     * @param resource a Resource
     * @return the quantity of the resource owned by the Player
     */
    public int getSellQuantity(Resource resource) {
        return vehicle.getCargoHold()[resource.ordinal()];
    }

    /**
     * Gets the buying price of a resource in the Player's current Room
     *
     * @param resource a Resource
     * @return the buy price of the resource
     */
    public int getBuyPrice(Resource resource) {
        return currentRoom.getBuyFromRoomPrices()[resource.ordinal()];
    }

    /**
     * Gets the selling price of a resource in the Player's current Room
     *
     * @param resource a Resource
     * @return the sell price of the resource
     */
    public int getSellPrice(Resource resource) {
        return currentRoom.getSellToRoomPrices()[resource.ordinal()];
    }

    /**
     * Calculates the maximum number of a certain Resource that the Player can buy
     *
     * Returns the minimum of the following options:
     *  -Number offered in the Player's current Room
     *  -Number that the Player can purchase with their credits
     *  -Number of cargo spaces left in the Player's Vehicle
     *
     *  Returns -1 if the Resource cannot be bought from the current Room
     *
     * @param resource the resource being purchased
     * @return the max buy quantity of the given resource`
     */
    public int calculateMaxBuyQuantity(Resource resource) {
        int[] buyQuantities = currentRoom.getBuyFromRoomQuantities();
        int maxBuyQuantity = buyQuantities[resource.ordinal()];

        int[] buyPrices = currentRoom.getBuyFromRoomPrices();
        int price = buyPrices[resource.ordinal()];
        int credits = player.getCredits();
        maxBuyQuantity = Math.min(credits / price, maxBuyQuantity);

        int remainingCargoSpace = vehicle.calculateRemainingCargoSpace();

        return Math.min(maxBuyQuantity, remainingCargoSpace);
    }

    /**
     * Calculates the maximum number of a certain Resource that the Player can sell
     * (just the number of that resource that they have in their cargoHold)
     *
     * Returns -1 if the Resource cannot be sold to the current Room
     *
     * @param resource the resource being purchased
     * @return the max sell quantity of the given resource
     */
    public int calculateMaxSellQuantity(Resource resource) {
        int[] cargoHold = vehicle.getCargoHold();
        return cargoHold[resource.ordinal()];
    }

    /**
     * Adds the given number of resources to the Player's cargoHold,
     * subtracts the cost from the Player's credits, and subtracts
     * the number bought from the current Room's buyFromRoomQuantities
     *
     * @param resource the Resource to buy
     * @param numToBuy how many to buy
     */
    public void buyResources(Resource resource, int numToBuy) {
        int[] cargoHold = vehicle.getCargoHold();
        cargoHold[resource.ordinal()] += numToBuy;
        vehicle.setCargoHold(cargoHold);

        int credits = player.getCredits();
        int costPerUnit = currentRoom.getBuyFromRoomPrices()[resource.ordinal()];
        player.setCredits(credits - (numToBuy * costPerUnit));

        int[] quantities = currentRoom.getBuyFromRoomQuantities();
        quantities[resource.ordinal()] -= numToBuy;
        currentRoom.setBuyFromRoomQuantities(quantities);

        Log.d("BUY", numToBuy + " " + resource.name()
                + "(s) bought for " + getBuyPrice(resource) + " credits each ("
                + numToBuy * getBuyPrice(resource) + " total). Player has "
                + player.getCredits() + " credits remaining.");
    }

    /**
     * Removes the given number of resources from the Player's cargoHold,
     * and adds the earnings to the Player's credits
     *
     * @param resource the Resource to sell
     * @param numToSell how many to sell
     */
    public void sellResources(Resource resource, int numToSell) {
        int[] cargoHold = vehicle.getCargoHold();
        cargoHold[resource.ordinal()] -= numToSell;
        vehicle.setCargoHold(cargoHold);

        int credits = player.getCredits();
        int payPerUnit = currentRoom.getSellToRoomPrices()[resource.ordinal()];
        player.setCredits(credits + (numToSell * payPerUnit));

        Log.d("SELL", numToSell + " " + resource.name()
                + "(s) sold for " + getSellPrice(resource) + " credits each ("
                + numToSell * getSellPrice(resource) + " total). Player has "
                + player.getCredits() + " credits remaining.");
    }

}
