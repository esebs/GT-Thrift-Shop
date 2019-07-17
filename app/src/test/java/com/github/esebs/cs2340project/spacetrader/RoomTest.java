package com.github.esebs.cs2340project.spacetrader;
import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Room;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is a test class for the createRooms and setBuyFromRoomQuantities method
 *
 * @author Jesus Gonzalez
 * @version 1.0
 */
public class RoomTest {
    private Room roomA;
    private Room roomB;
    private Building b;


    @Before
    public void setup(){
        b = new Building("buildingA",.2,.2, new ArrayList<String>());
        roomA = new Room("roomA", b);
        roomB = new Room("roomB", b);
    }


    @Test
    public void createRooms() {
        List<String> strings = new ArrayList<>();
        strings.add("roomC");
        strings.add("roomD");

        List<String> names = new ArrayList<>();

        for(Room room: roomA.createRooms(strings, b)){
            names.add(room.getName());
        }

        assertTrue(names.contains("roomC"));
        assertTrue(names.contains("roomD"));
    }

    @Test
    public void createRooms2() {
        List<String> strings = new ArrayList<>();
        strings.add("roomC");
        strings.add("roomD");

        List<String> names = new ArrayList<>();
        for(Room room: roomB.createRooms(strings, b)){
            names.add(room.getName());
        }

        assertFalse(names.contains("roomE"));
        assertFalse(names.contains("roomF"));
    }

    @Test
    public void setBuyFromRoomQuantities() {
        int[] quantities = {100,20,30,40,1};
        roomA.setBuyFromRoomQuantities(quantities);


        assertEquals(roomA.getBuyFromRoomQuantities(), quantities);
        assertEquals(roomA.getBuyFromRoomQuantities().length, 5);

    }
    @Test
    public void setBuyFromRoomQuantities2() {
        int[] quantities = {100,20,30,40,1};
        roomA.setBuyFromRoomQuantities(quantities);

        int[] quantites2 = {100,20,30,40};


        assertFalse(roomA.getBuyFromRoomQuantities().equals(quantites2));

    }
}