package com.github.esebs.cs2340project.spacetrader.viewmodels;

import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.model.Model;

public class TradingViewModel {

    private Model model = Model.getModelInstance();

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
     * @return the max buyable number of the given resource
     */
    public int calculateMaxBuyable(Resource resource) {
        int[] buyQuantities = model.getPlayer().getCurrent().getBuyFromRoomQuantities();
        int numBuyable = buyQuantities[resource.ordinal()];

        int[] buyPrices = model.getPlayer().getCurrent().getBuyFromRoomPrices();
        int price = buyPrices[resource.ordinal()];
        int credits = model.getPlayer().getCredits();
        numBuyable = Math.min(credits / price, numBuyable);

        int remainingCargoSpace = model.getPlayer().getVehicle().calculateRemainingCargoSpace();

        return Math.min(numBuyable, remainingCargoSpace);
    }

    /**
     * Calculates the maximum number of a certain Resource that the Player can sell
     * (just the number of that resource that they have in their cargoHold)
     *
     * Returns -1 if the Resource cannot be sold to the current Room
     *
     * @param resource the resource being purchased
     * @return the max sellable number of the given resource
     */
    public int calculateMaxSellable(Resource resource) {
        int[] cargoHold = model.getPlayer().getVehicle().getCargoHold();
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
        int[] cargoHold = model.getPlayer().getVehicle().getCargoHold();
        cargoHold[resource.ordinal()] += numToBuy;
        model.getPlayer().getVehicle().setCargoHold(cargoHold);

        int credits = model.getPlayer().getCredits();
        int costPerUnit = model.getPlayer().getCurrent().getBuyFromRoomPrices()[resource.ordinal()];
        model.getPlayer().setCredits(credits - (numToBuy * costPerUnit));

        int[] quantities = model.getPlayer().getCurrent().getBuyFromRoomQuantities();
        quantities[resource.ordinal()] -= numToBuy;
        model.getPlayer().getCurrent().setBuyFromRoomQuantities(quantities);
    }

    /**
     * Removes the given number of resources from the Player's cargoHold,
     * and adds the earnings to the Player's credits
     *
     * @param resource the Resource to sell
     * @param numToSell how many to sell
     */
    public void sellResources(Resource resource, int numToSell) {
        int[] cargoHold = model.getPlayer().getVehicle().getCargoHold();
        cargoHold[resource.ordinal()] -= numToSell;
        model.getPlayer().getVehicle().setCargoHold(cargoHold);

        int credits = model.getPlayer().getCredits();
        int payPerUnit = model.getPlayer().getCurrent().getSellToRoomPrices()[resource.ordinal()];
        model.getPlayer().setCredits(credits + (numToSell * payPerUnit));
    }
}
