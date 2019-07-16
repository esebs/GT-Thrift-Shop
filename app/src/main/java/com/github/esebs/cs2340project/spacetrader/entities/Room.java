package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents a room inside one of the map's buildings
 */
public final class Room {

    // Room characteristics
    private final String name;
    private final Building building;
    private final Size size;
    private final TechLevel techLevel;
    private final Government government;
    private final ResourceLevel resource;
    private final PolicePresence policePresence;

    // Room trading arrays
    private final int[] buyFromRoomPrices;
    private final int[] sellToRoomPrices;
    private int[] buyFromRoomQuantities;

    private Room(String name, Building building) {
        this.name = name;
        this.building = building;
        size = Size.generateSize();
        techLevel = TechLevel.generateTechLevel();
        government = Government.generateGovernment();
        resource = ResourceLevel.generateResources();
        policePresence = PolicePresence.generatePolicePresence();
        buyFromRoomPrices = createBuyFromRoomPrices();
        sellToRoomPrices = createSellToRoomPrices();
        buyFromRoomQuantities = createRandomQuantities();
    }

    /**
     * Takes a List of room name strings and converts it to a list of Room objects
     *
     * @param roomNumbers a List of strings of the room numbers
     * @param building the Building object the Room(s) are part of
     * @return a List of Room objects
     */
    public static List<Room> createRooms(Iterable<String> roomNumbers, Building building) {
        List<Room> rooms = new ArrayList<>();
        for (String roomNumber : roomNumbers) {
            rooms.add(new Room(roomNumber, building));
        }
        return rooms;
    }

    /**
     * @return the prices to buy from this Room
     */
    public int[] getBuyFromRoomPrices() {
        return buyFromRoomPrices.clone();
    }

    /**
     * @return the prices to sell to this Room
     */
    public int[] getSellToRoomPrices() {
        return sellToRoomPrices.clone();
    }

    /**
     * @return the quantities of resources being sold
     */
    public int[] getBuyFromRoomQuantities() {
        return buyFromRoomQuantities.clone();
    }

    /**
     * @return name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * @return Building this Room is inside of
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * @return size of the room
     */
    public Size getSize() {
        return size;
    }

    /**
     * @return the tech level of the room
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * @return the government type of the room
     */
    public Government getGovernment() {
        return government;
    }

    /**
     * @return get the resource level of the room
     */
    public ResourceLevel getResource() {
        return resource;
    }

    /**
     * @return get the police presence
     */
    public PolicePresence getPolicePresence() {
        return policePresence;
    }


    /**
     * Sets the room's resource quantities
     * @param buyFromRoomQuantities array of resource quantities
     */
    public void setBuyFromRoomQuantities(int[] buyFromRoomQuantities) {
        this.buyFromRoomQuantities = buyFromRoomQuantities.clone();
    }

    /**
     * Calculates the buy price for each resource based on the Room's tech level
     * @return an array of the resources' buy prices
     */
    private int[] createBuyFromRoomPrices() {
        int[] buyPrices = new int[10];
        for (Resource resource: Resource.values()) {
            int price;
            TechLevel mtlp = resource.getMtlp();
            if (techLevel.ordinal() >= mtlp.ordinal()) {
                price = resource.buyPriceCalc(techLevel);
            } else {
                price = -1;
            }
            buyPrices[resource.ordinal()] = price;
        }
        return buyPrices;
    }

    /**
     * The sell price is simply 95% of the buy price (if the Room has the tech level required
     * to sell that resource, otherwise i's -1)
     * @return an array of the resources' sell prices
     */
    private int[] createSellToRoomPrices() {
        int[] sellPrices = new int[10];
        final double percentageOfBuyPrice = 0.95;
        for (Resource resource: Resource.values()) {
            int price;
            // If the Player can sell this resource to the Room
            TechLevel mtlu = resource.getMtlu();
            if (techLevel.ordinal() >= mtlu.ordinal()) {
                int buyPrice = buyFromRoomPrices[resource.ordinal()];
                // If the Player cannot buy this resource from the Room, then calculate a sell price
                if (buyPrice == -1) {
                    price = resource.sellPriceCalc(techLevel);
                    // Otherwise, the sell price is just a discount of the current buy price
                } else {
                    price = (int) (buyPrice * percentageOfBuyPrice);
                }
                // If the Player cannot sell this resource to the Room
            } else {
                price = -1;
            }
            sellPrices[resource.ordinal()] = price;
        }
        return sellPrices;
    }

    /**
     * Sets the room's resources to random quantities within [1, 50]
     * If the Room cannot produce a resource, that resource's quantity will be -1
     *
     * @return an array of the resources' quantities
     */
    private int[] createRandomQuantities() {
        Random r = new Random();
        int[] quantities = new int[10];
        final int maxQuantities = 50;
        for (Resource resource: Resource.values()) {
            int quantity;
            TechLevel mtlp = resource.getMtlp();
            if (techLevel.ordinal() >= mtlp.ordinal()) {
                quantity = r.nextInt(maxQuantities) + 1;
            } else {
                quantity = -1;
            }
            quantities[resource.ordinal()] = quantity;
        }
        return quantities;
    }

    /**
     * toString method for Room
     *
     * @return string representation of this Room
     */
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", building=" + building.getName() +
                ", size=" + size +
                ", techLevel=" + techLevel +
                ", government=" + government +
                ", resource=" + resource +
                ", policePresence=" + policePresence +
                ", sellToRoomPrices=" + Arrays.toString(sellToRoomPrices) +
                ", buyFromRoomPrices=" + Arrays.toString(buyFromRoomPrices) +
                ", buyFromRoomQuantities=" + Arrays.toString(buyFromRoomQuantities) +
                '}';
    }

}
