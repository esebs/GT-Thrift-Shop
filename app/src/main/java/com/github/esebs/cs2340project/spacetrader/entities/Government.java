package com.github.esebs.cs2340project.spacetrader.entities;

import java.util.Random;

public enum Government {
    PACIFIST_STATE,
    EGALITARIAN_STATE,
    UTOPIAN_STATE,
    STELLAR_EMPIRE,
    TYRANNICAL_STATE,
    COLONY_WORLD,
    ALIEN_WORLD;

    public static Government generateGovernment() {
        Random random = new Random();
        int key = random.nextInt(7);
        switch (key) {
            case 0:
                return Government.PACIFIST_STATE;

            case 1:
                return Government.EGALITARIAN_STATE;

            case 2:
                return Government.TYRANNICAL_STATE;

            case 3:
                return Government.UTOPIAN_STATE;

            case 4:
                return Government.ALIEN_WORLD;

            case 5:
                return Government.COLONY_WORLD;

            default:
                return Government.STELLAR_EMPIRE;

        }
    }
}
