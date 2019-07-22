package com.github.esebs.cs2340project.spacetrader.entities;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import com.github.esebs.cs2340project.spacetrader.views.MainActivity;

import java.util.Random;

public class AlbinoSquirrel extends Encounterable {
    private Model model;
    private Player player;
    double die = Math.random();

    public AlbinoSquirrel(){
        model = Model.getModelInstance();
        player = model.getPlayer();
    }


    public boolean doesKill() {
        if (die >= .5) {
            player.getVehicle().setCurrentHealth(0);
            return true;
        } else {
            return false;
        }
    }
}
