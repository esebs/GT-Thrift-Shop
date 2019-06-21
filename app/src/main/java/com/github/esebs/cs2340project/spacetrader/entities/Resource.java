package com.github.esebs.cs2340project.spacetrader.entities;
import java.util.Random;

public enum Resource {
    NO_SPECIAL_RESOURCES,
    MINERAL_RICH,
    MINERAL_POOR,
    DESERT,
    LOTS_OF_WATER,
    RICH_SOIL,
    POOR_SOIL,
    RICH_FAUNA,
    LIFELESS,
    WEIRD_MUSHROOMS,
    LOTS_OF_HERBS,
    ARTISTIC,
    WARLIKE;

    public static Resource generateResources() {
        Random random = new Random();
        int key = random.nextInt(20);
        switch (key) {
            case 1: return Resource.MINERAL_RICH;

            case 2: return Resource.MINERAL_POOR;

            case 3: return Resource.DESERT;

            case 4: return Resource.LOTS_OF_WATER;

            case 5: return Resource.RICH_SOIL;

            case 6: return Resource.POOR_SOIL;

            case 7: return Resource.RICH_FAUNA;

            case 8: return Resource.LIFELESS;

            case 9: return Resource.WEIRD_MUSHROOMS;

            case 10: return Resource.LOTS_OF_HERBS;

            case 11: return Resource.ARTISTIC;

            case 12: return Resource.WARLIKE;

            default: return Resource.NO_SPECIAL_RESOURCES;

        }
    }
}
