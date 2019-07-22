package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.Random;

/**
 * Represents a trader that the player can encounter while traveling
 */
public class Trader extends Encounterable {

    private Resource resource;
    private int quantity;
    private int price;

    /**
     * Constructor for Trader class
     */
    public Trader() {
        Random random = new Random();

        int randomIndex = random.nextInt(10);
        this.resource = Resource.values()[randomIndex];

        this.quantity = random.nextInt(15) + 1;

        int basePrice = resource.getBasePrice();
        this.price = basePrice - (int)(basePrice * player.getTraderPoints() * 0.01);

        this.health = 25;
        this.vehicleName = Vehicle.UNICYCLE.getVehicleType();
    }

    /**
     * Calculates the maximum number of the Trader's resource that the Player can buy
     *
     * Returns the minimum of the following options:
     *  -Number offered by the Trader
     *  -Number that the Player can purchase with their credits
     *  -Number of cargo spaces left in the Player's Vehicle
     *
     * @return the max buy quantity of the given resource`
     */
    public int calculateMaxBuyQuantity() {

        int credits = player.getCredits();
        int maxBuyQuantity = Math.min(credits / price, quantity);

        int remainingCargoSpace = player.calculateRemainingCargoSpace();

        return Math.min(maxBuyQuantity, remainingCargoSpace);
    }

    /**
     * Gets the Resource that this Trader sells
     *
     * @return trader's Resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Gets the quantity of the Trader's Resource
     *
     * @return quantity of Trader Resource
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price per unit of the Trader's Resource
     * @return resource price
     */
    public int getPrice() {
        return price;
    }
}
