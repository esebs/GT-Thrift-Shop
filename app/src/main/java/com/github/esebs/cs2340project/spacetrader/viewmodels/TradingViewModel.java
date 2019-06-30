package com.github.esebs.cs2340project.spacetrader.viewmodels;

import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.model.Model;

public class TradingViewModel {

    private Model model = Model.getModelInstance();

    public int calculateMaxBuyable(Resource resource) {
        int max = 0;
        return max;
    }

    public int calculateMaxSellable(Resource resource) {
        int max = 0;
        return max;
    }

    public void buyResources(Resource resource, int numToBuy) {
        int[] cargoHold = model.getPlayer().getVehicle().getCargoHold();
        cargoHold[resource.ordinal()] += numToBuy;
        model.getPlayer().getVehicle().setCargoHold(cargoHold);
    }

    public void sellResources(Resource resource, int numToSell) {
        int[] cargoHold = model.getPlayer().getVehicle().getCargoHold();
        cargoHold[resource.ordinal()] -= numToSell;
        model.getPlayer().getVehicle().setCargoHold(cargoHold);
    }
}
