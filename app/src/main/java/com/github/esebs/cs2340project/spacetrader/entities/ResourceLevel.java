package com.github.esebs.cs2340project.spacetrader.entities;
import java.util.Random;

/**
 * Enum for building resource levels
 */
public enum ResourceLevel {
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

    /**
     * Chooses a random resource level
     * @return resource level
     */
    public static ResourceLevel generateResources() {
        Random random = new Random();
        int key = random.nextInt(20);
        switch (key) {
            case 1: return ResourceLevel.MINERAL_RICH;

            case 2: return ResourceLevel.MINERAL_POOR;

            case 3: return ResourceLevel.DESERT;

            case 4: return ResourceLevel.LOTS_OF_WATER;

            case 5: return ResourceLevel.RICH_SOIL;

            case 6: return ResourceLevel.POOR_SOIL;

            case 7: return ResourceLevel.RICH_FAUNA;

            case 8: return ResourceLevel.LIFELESS;

            case 9: return ResourceLevel.WEIRD_MUSHROOMS;

            case 10: return ResourceLevel.LOTS_OF_HERBS;

            case 11: return ResourceLevel.ARTISTIC;

            case 12: return ResourceLevel.WARLIKE;

            default: return ResourceLevel.NO_SPECIAL_RESOURCES;

        }
    }
}
