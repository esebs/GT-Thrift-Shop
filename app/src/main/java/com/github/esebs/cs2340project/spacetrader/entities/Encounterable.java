package com.github.esebs.cs2340project.spacetrader.entities;

import com.github.esebs.cs2340project.spacetrader.model.Model;

/**
 * A vehicle that the player can encounter while traveling
 */
public abstract class Encounterable {

    private Model model;
    protected Player player;
    protected Vehicle vehicle;

    /**
     * Constructor for Encounterable
     */
    public Encounterable() {
        model = Model.getModelInstance();
        player = model.getPlayer();
        vehicle = Vehicle.UNICYCLE;
    }

    /**
     * Make the vehicle attack the player
     * 
     * @return True if the vehicle attacks the player, False if it misses
     */
    public boolean attack() {
        int damage = 10;
        boolean attacks;

        Difficulty difficulty = player.getDifficulty();
        int multiple = difficulty.getMultiple();
        double missingChance = 1.0 - multiple*0.25;

        double randomChance = Math.random();

        if (randomChance > missingChance) {
            attacks = true;
            Vehicle playerVehicle = player.getVehicle();
            int currentHealth = playerVehicle.getCurrentHealth();
            playerVehicle.setCurrentHealth(currentHealth - damage);
        } else {
            attacks = false;
        }
        return attacks;
    }

    /**
     * Get the vehicle represented by this Encounterable object
     * @return
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * If the encounterable object is dead
     *
     * @return true if dead
     */
    public boolean isDead() {
        return vehicle.getCurrentHealth() == 0;
    }

}
