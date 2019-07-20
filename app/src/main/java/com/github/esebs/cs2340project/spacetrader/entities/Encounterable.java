package com.github.esebs.cs2340project.spacetrader.entities;

import com.github.esebs.cs2340project.spacetrader.model.Model;

/**
 * A vehicle that the player can encounter while traveling
 */
public abstract class Encounterable {

    private Model model;
    protected Player player;
    protected int health;
    protected String vehicleName;

    /**
     * Constructor for Encounterable
     */
    public Encounterable() {
        model = Model.getModelInstance();
        player = model.getPlayer();
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
     * Get the health of this Encounterable object
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set this Encounterable's health
     * @param newHealth new health
     */
    public void setHealth(int newHealth) {
        health = newHealth > 0 ? newHealth : 0;
    }

    /**
     * Get this encounterable's vehicle name
     * @return name of vehicle
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * If the encounterable object is dead
     *
     * @return true if dead
     */
    public boolean isDead() {
        return health == 0;
    }

}
