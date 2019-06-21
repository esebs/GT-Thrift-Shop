package com.github.esebs.cs2340project.spacetrader.entities;
import android.os.TestLooperManager;

import java.util.Random;

public enum TechLevel {
    PRE_AGRICULTURE,
    AGRICULTURE,
    MEDIEVAL,
    RENAISSANCE,
    EARLY_INDUSTRIAL,
    INDUSTRIAL,
    POST_INDUSTRIAL,
    HI_TECH;

    public static TechLevel generateTechLevel() {
        Random random = new Random();
        int key = random.nextInt(8);
        switch (key) {
            case 0: return TechLevel.PRE_AGRICULTURE;

            case 1: return TechLevel.AGRICULTURE;

            case 2: return TechLevel.MEDIEVAL;

            case 3: return TechLevel.RENAISSANCE;

            case 4: return TechLevel.EARLY_INDUSTRIAL;

            case 5: return TechLevel.INDUSTRIAL;

            case 6: return TechLevel.POST_INDUSTRIAL;

            default: return TechLevel.HI_TECH;

        }
    }
}
