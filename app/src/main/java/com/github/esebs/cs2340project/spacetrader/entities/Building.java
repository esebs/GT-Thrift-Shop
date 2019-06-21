package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.ArrayList;

public enum Building {
    CULC ("Clough Undergraduate Learning Commons"),
    KLAUS ("Klaus Advanced Computing Building"),
    SCHELLER ("Scheller College of Business"),
    MASON ("Mason Building"),
    IC ("Instructional Center"),
    SKILES ("Skiles Classroom Building"),
    CRC ("Campus Recreation Center"),
    ARCH ("Architecture Building"),
    SC ("Student Center"),
    FORD("Ford ES&T Building");

    private String name;
    private ArrayList<Room> rooms;

    /**
     * Creates a Building
     * @param name String name for the Building enum
     */
    Building (String name) {
        this.name = name;
    }


    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
