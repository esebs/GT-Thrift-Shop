package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.Random;

/**
 * Enum for possible building sizes
 */
public enum Size {
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    HUGE;

    /**
     * Chooses a random building size
     * @return size
     */
    public static Size generateSize() {
        Random random = new Random();
        int key = random.nextInt(9);
        switch (key) {
            case 0: return Size.TINY;
            case 1:
            case 2: return Size.SMALL;
            case 6:
            case 7: return Size.LARGE;
            case 8: return Size.HUGE;
            default: return Size.MEDIUM;
        }
    }
}
