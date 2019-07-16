package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ViewModel for universe creation
 */
public class CreateUniverseViewModel {
    private final Model model = Model.getModelInstance();

    /**
     * Creates a random set of buildings and sets it as the model's buildings
     */
    public void createUniverse() {
        // Instantiate the universe's buildings
        model.setBuildings(createBuildings());

        Log.d("APP", "BuildingViewModel: universe created: " + model);
    }

    /**
     * Creates the universe's buildings (along with their rooms)
     * @return a list of the universe's buildings
     */
    private List<Building> createBuildings() {

        List<Building> buildings = new ArrayList<>();

        // Create CULC
        final double culcLatitude = 33.774889;
        final double culcLongitude = -84.396417;
        Building culc = new Building("Clough Undergraduate Learning Commons",
                culcLatitude, culcLongitude, Arrays.asList("152", "244"));
        buildings.add(culc);

        // Create Klaus
        final double klausLatitude = 33.777167;
        final double klausLongitude = -84.396239;
        Building klaus = new Building("Klaus Advanced Computing Building",
                klausLatitude, klausLongitude, Arrays.asList("1443", "2443"));
        buildings.add(klaus);

        // Create Scheller
        final double schellerLatitude = 33.776370;
        final double schellerLongitude = -84.388151;
        Building scheller = new Building("Scheller College of Business",
                schellerLatitude, schellerLongitude, Arrays.asList("254", "432"));
        buildings.add(scheller);

        // Create Mason
        final double masonLatitude = 33.776616;
        final double masonLongitude = -84.398816;
        Building mason = new Building("Mason Building",
                masonLatitude,  masonLongitude, Arrays.asList("101", "23"));
        buildings.add(mason);

        // Create Instructional Center
        final double icLatitude = 33.775434;
        final double icLongitude = -84.401269;
        Building ic = new Building("Instructional Center",
                icLatitude, icLongitude, Arrays.asList("205", "105"));
        buildings.add(ic);

        // Create Skiles
        final double skilesLatitude = 33.773587;
        final double skilesLongitude = -84.396334;
        Building skiles = new Building("Skiles Classroom Building",
                skilesLatitude, skilesLongitude, Arrays.asList("314", "317"));
        buildings.add(skiles);

        // Create CRC
        final double crcLatitude = 33.775633;
        final double crcLongitude = -84.403793;
        Building crc = new Building("Campus Recreation Center",
                crcLatitude, crcLongitude, Arrays.asList("5", "110"));
        buildings.add(crc);

        // Create Architecture
        final double archLatitude = 33.776049;
        final double archLongitude = -84.395721;
        Building arch = new Building("Architecture Building",
                archLatitude,  archLongitude, Arrays.asList("123", "201"));
        buildings.add(arch);

        // Create Student Center
        final double scLatitude = 33.773815;
        final double scLongitude = -84.398708;
        Building sc = new Building("Student Center",
                scLatitude, scLongitude, Arrays.asList("Piedmont", "69"));
        buildings.add(sc);

        // Create Ford ES&T
        final double fordLatitude = 33.778795;
        final double fordLongitude = -84.395953;
        Building ford = new Building("Ford ES&T Building",
                fordLatitude,  fordLongitude, Arrays.asList("341", "122"));
        buildings.add(ford);

        return buildings;
    }

}
