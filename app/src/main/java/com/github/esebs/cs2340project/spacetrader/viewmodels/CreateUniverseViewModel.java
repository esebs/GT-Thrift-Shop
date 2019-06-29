package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateUniverseViewModel {
    private Model model = Model.getModelInstance();

    public List<Building> getBuildings() {
        return Model.getModelInstance().getBuildings();
    }

    public void createUniverse() {
        // Instantiate the universe's buildings
        model.setBuildings(createBuildings());

        Log.d("APP", "BuildingViewModel: universe created: " + model);
    }

    private List<Building> createBuildings() {

        List<Building> buildings = new ArrayList<>();

        // Create CULC
        Building culc = new Building("Clough Undergraduate Learning Commons",
                33.774889, -84.396417, Arrays.asList("152", "244"));
        buildings.add(culc);

        // Create Klaus
        Building klaus = new Building("Klaus Advanced Computing Building",
                33.777167, -84.396239, Arrays.asList("1443", "2443"));
        buildings.add(klaus);

        // Create Scheller
        Building scheller = new Building("Scheller College of Business",
                33.776370, -84.388151, Arrays.asList("254", "432"));
        buildings.add(scheller);

        // Create Mason
        Building mason = new Building("Mason Building",
                33.776616,  -84.398816, Arrays.asList("101", "23"));
        buildings.add(mason);

        // Create Instructional Center
        Building ic = new Building("Instructional Center",
                33.775434, -84.401269, Arrays.asList("205", "105"));
        buildings.add(ic);

        // Create Skiles
        Building skiles = new Building("Skiles Classroom Building",
                33.773587, -84.396334, Arrays.asList("314", "317"));
        buildings.add(skiles);

        // Create CRC
        Building crc = new Building("Campus Recreation Center",
                33.775633, -84.403793, Arrays.asList("5", "110"));
        buildings.add(crc);

        // Create Architecture
        Building arch = new Building("Architecture Building",
                33.776049,  -84.395721, Arrays.asList("123", "201"));
        buildings.add(arch);

        // Create Student Center
        Building sc = new Building("Student Center",
                33.773815, -84.398708, Arrays.asList("Piedmont", "69"));
        buildings.add(sc);

        // Create Ford ES&T
        Building ford = new Building("Ford ES&T Building",
                33.778795,  -84.395953, Arrays.asList("341", "122"));
        buildings.add(ford);

        return buildings;
    }
}
