package com.github.esebs.cs2340project.spacetrader;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.entities.TechLevel;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravisUnitTests {

    @Test
    public void testCreateBuyFromRoomPrices() {
        Building building  = new Building("TestBuildingName", 69, 69,
                Arrays.asList("TestRoomName1", "TestRoomName2", "TestRoomName3"));
        List<Room> rooms = building.getRooms();

        List<List> calculatedLists = new ArrayList<>();
        List<List> actualLists = new ArrayList<>();

        for (Room room : rooms) {
            int[] buyFromRoomPrices = room.getBuyFromRoomPrices();

            // calculatedIndices holds the indices into buyFromRoomPrices of all resources which
            // have minimum tech levels to produce (MTLPs) that are greater than the room's
            // tech level (meaning the resources cannot be bought from the room).
            List<Integer> calculatedIndices = new ArrayList<>();
            for (Resource resource: Resource.values()) {
                TechLevel mtlp = resource.getMtlp();
                if (mtlp.ordinal() > room.getTechLevel().ordinal()) {
                    calculatedIndices.add(resource.ordinal());
                }
            }

            // actualIndices holds the indices into buyFromRoomPrices of all resources which have
            // a price of -1, which should only occur when a resource cannot be bought from
            // the room.
            List<Integer> actualIndices = new ArrayList<>();
            for (int i = 0; i < buyFromRoomPrices.length; i++) {
                if (buyFromRoomPrices[i] == -1) {
                    actualIndices.add(i);
                }
            }

            calculatedLists.add(calculatedIndices);
            actualLists.add(actualIndices);

        }

        System.out.println("Indices in each room where price should be -1: " + calculatedLists);
        System.out.println("Actual indices in each room where price is -1: " + actualLists);

        Assert.assertEquals(calculatedLists, actualLists);
    }

    @Test
    public void testCalculateRemainingCargoSpace() {
        int[] testCargoHold = {6, 9, 4, 2, 0, 7, 3, 0, 1, 5};

        Vehicle golfCart = Vehicle.GOLF_CART;
        golfCart.setCargoHold(testCargoHold);
        int actualRemainingCargoSpace = golfCart.calculateRemainingCargoSpace();

        int numSpacesOccupied = 0;
        for (int i = 0; i < testCargoHold.length; i++) {
            numSpacesOccupied += testCargoHold[i];
        }
        int maxCargoSpace = golfCart.getCargoSize();
        int remainingCargoSpace = maxCargoSpace - numSpacesOccupied;

        System.out.println("Remaining cargo space should be: " + remainingCargoSpace
                + " out of " + golfCart.getCargoSize());
        System.out.println("Actual remaining cargo space is: " + actualRemainingCargoSpace
                + " out of " + golfCart.getCargoSize());

        Assert.assertEquals(actualRemainingCargoSpace, remainingCargoSpace);
    }
}
