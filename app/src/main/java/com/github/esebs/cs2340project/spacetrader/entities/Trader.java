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

        this.vehicle = Vehicle.UNICYCLE;
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
