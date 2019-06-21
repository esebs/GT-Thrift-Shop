package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.ArrayList;
import java.util.List;

public class Room {

    // Room characteristics
    private String name;
    private Size size;
    private TechLevel techLevel;
    private Government government;
    private Resource resource;
    private PolicePresence policePresence;

    public Room(String name) {
        this.name = name;
        size = Size.generateSize();
        techLevel = TechLevel.generateTechLevel();
        government = Government.generateGovernment();
        resource = Resource.generateResources();
        policePresence = PolicePresence.generatePolicePresence();
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
     * toString method for Room
     *
     * @return string representation of this Room
     */
    @Override
    public String toString() {
        return "\nRoom{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", techLevel=" + techLevel +
                ", government=" + government +
                ", resource=" + resource +
                ", policePresence=" + policePresence +
                "}";
    }
}
