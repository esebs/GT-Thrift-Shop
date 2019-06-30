package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Room {

    // Room characteristics
    private String name;
    private Size size;
    private TechLevel techLevel;
    private Government government;
    private ResourceLevel resource;
    private PolicePresence policePresence;

    // Room trading arrays
    private int[] sellToRoomPrices;
    private int[] buyFromRoomPrices;
    private int[] buyFromRoomQuantities;

    public Room(String name) {
        this.name = name;
        size = Size.generateSize();
        techLevel = TechLevel.generateTechLevel();
        government = Government.generateGovernment();
        resource = ResourceLevel.generateResources();
        policePresence = PolicePresence.generatePolicePresence();
        sellToRoomPrices = createSellToRoomPrices();
        buyFromRoomPrices = createBuyFromRoomPrices();
        buyFromRoomQuantities = createRandomQuantities();
    }

    /**
     * Takes a List of room name strings and converts it to a list of Room objects
     *
     * @param roomNumbers a List of strings of the room numbers
     * @return a List of Room objects
     */
    public static List<Room> createRooms(List<String> roomNumbers) {
        List<Room> rooms = new ArrayList<>();
        for (String roomNumber : roomNumbers) {
            rooms.add(new Room(roomNumber));
        }
        return rooms;
    }

    /**
     * @return the prices to sell to this Room
     */
    public int[] getSellToRoomPrices() {
        return sellToRoomPrices;
    }

    /**
     * @return the prices to buy from this Room
     */
    public int[] getBuyFromRoomPrices() {
        return buyFromRoomPrices;
    }

    /**
     * @return the quantities of resources being sold
     */
    public int[] getBuyFromRoomQuantities() {
        return buyFromRoomQuantities;
    }

    /**
     * Sets the room's resource quantities
     * @param buyFromRoomQuantities array of resource quantities
     */
    public void setBuyFromRoomQuantities(int[] buyFromRoomQuantities) {
        this.buyFromRoomQuantities = buyFromRoomQuantities;
    }

    /**
     * Calculates the sell price for each resource based on the Room's tech level
     * @return an array of the resources' sell prices
     */
    private int[] createSellToRoomPrices() {
        int[] sellPrices = new int[10];
        for (Resource resource: Resource.values()) {
            int price;
            if (techLevel.ordinal() >= resource.getMtlu().ordinal()) {
                price = resource.sellPriceCalc(techLevel);
            } else {
                price = -1;
            }
            sellPrices[resource.ordinal()] = price;
        }
        return sellPrices;
    }

    /**
     * Calculates the buy price for each resource based on the Room's tech level
     * @return an array of the resources' buy prices
     */
    private int[] createBuyFromRoomPrices() {
        int[] buyPrices = new int[10];
        for (Resource resource: Resource.values()) {
            int price;
            if (techLevel.ordinal() >= resource.getMtlp().ordinal()) {
                price = resource.buyPriceCalc(techLevel);
            } else {
                price = -1;
            }
            buyPrices[resource.ordinal()] = price;
        }
        return buyPrices;
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
        for (Resource resource: Resource.values()) {
            int quantity;
            if (techLevel.ordinal() >= resource.getMtlp().ordinal()) {
                quantity = r.nextInt(50) + 1;
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
