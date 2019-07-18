package com.github.esebs.cs2340project.spacetrader.entities;

import com.github.esebs.cs2340project.spacetrader.model.Model;

public abstract class Encounterable {

    private Model model;
    public Player player;
    private Vehicle vehicle;

    public Encounterable() {
        model = Model.getModelInstance();
        player = model.getPlayer();
    }

    public Boolean attack() {
        boolean attacks;

        Difficulty difficulty = player.getDifficulty();
        int multiple = difficulty.getMultiple();
        double missingChance = 1.0 - multiple*0.25;

        double randomChance = Math.random();

        if (randomChance > missingChance) {
            attacks = true;
            Vehicle playerVehicle = player.getVehicle();
        } else {
            attacks = false;
        }

        return attacks;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

}
