package com.github.esebs.cs2340project.spacetrader.entities;
import android.widget.RadioGroup;

import java.util.Random;

public enum PolicePresence {
    NO_POLICE,
    LIGHT_POLICE,
    STANDARD_POLICE,
    HEAVY_POLICE,
    POLICE_STATE,
    CORRUPT_POLICE;

    public static PolicePresence generatePolicePresence() {
        Random random = new Random();
        int key = random.nextInt(10);
        switch (key) {
            case 0: return PolicePresence.NO_POLICE;
            case 1:
            case 2: return PolicePresence.LIGHT_POLICE;
            case 6:
            case 7: return PolicePresence.HEAVY_POLICE;
            case 8: return PolicePresence.POLICE_STATE;
            case 9: return PolicePresence.CORRUPT_POLICE;
            default: return PolicePresence.STANDARD_POLICE;
        }
    }
}
