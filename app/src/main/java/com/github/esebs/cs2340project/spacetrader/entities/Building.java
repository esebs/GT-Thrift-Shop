package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.List;

public class Building {

    // Building characteristics
    private String name;
    private double latitude;
    private double longitude;
    private List<Room> rooms;

    /**
     * Creates a Building
     *
     * @param name String name for the Building enum
     * @param latitude latitude of the Building
     * @param longitude longitude of the Building
     * @param roomNumbers a List of strings of the building's room numbers
     */
    public Building (String name, double latitude, double longitude, List<String> roomNumbers) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rooms = Room.createRooms(roomNumbers);
    }

    /**
     * Gets the Building's name
     *
     * @return Building name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Building's latitude
     *
     * @return Building latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the Building's longitude
     *
     * @return Building longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets a list of the Building's rooms
     *
     * @return the Building's rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * toString method for Building
     *
     * @return string representation of this Building
     */
    @Override
    public String toString() {
        String temp = "";
        for (Room room : rooms) {
            temp += "\n\t" + room.toString();
        }

        return "\nBuilding{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", rooms=" + temp +
                "}\n";
    }
}
