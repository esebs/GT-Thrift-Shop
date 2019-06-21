package com.github.esebs.cs2340project.spacetrader.viewmodels;

import android.util.Log;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import java.util.List;

public class CreateUniverseViewModel {
    private Model model = Model.getModelInstance();

    public List<Building> getBuildings() {
        return Model.getModelInstance().getBuildings();
    }

    public void createUniverse() {
        Log.d("APP", "BuildingViewModel: universe created: " + model);
    }
}
