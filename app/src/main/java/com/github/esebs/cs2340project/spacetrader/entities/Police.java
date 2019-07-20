package com.github.esebs.cs2340project.spacetrader.entities;

public class Police extends Encounterable {
    private int[] playerItems = player.getVehicle().getCargoHold();
    private int firearmsIndex = Resource.FIREARMS.ordinal();
    private int narcoticsIndex = Resource.NARCOTICS.ordinal();
    private int playerCredits = player.getCredits();
    private int difficulty = player.getDifficulty().getMultiple();
    private double random = Math.random();

    public Police() {
        this.vehicle = Vehicle.MOPED;
    }

    /**
     * This method will examine the Player cargo hold.
     * If there are firearms and/or narcotics in the
     * cargo hold, the Police will take them away and
     * fine the player 25% of there credits.
     *
     * @return true if illegal goods are found, false otherwise
     */
    public boolean searchCargoHold() {
        if (playerItems[firearmsIndex] != 0 || playerItems[narcoticsIndex] != 0) {
            //take away firearms and/or narcotics
            playerItems[firearmsIndex] = 0;
            playerItems[narcoticsIndex] = 0;
            player.getVehicle().setCargoHold(playerItems);

            //fine the player 25% of there credits
            player.setCredits(playerCredits - (int) (playerCredits * .25));


            return true;
        } else {
            return false;
        }
    }

    /**
     * The bribe amount is calculated
     *
     */
    public boolean takesBribe() {
        if (random >= .5) {
            player.setCredits(playerCredits - getBribeAmount());
            return true;
        } else {
            return false;
        }
    }

    /**
     * The bribe amount is calculated
     *
     * @return the
     */
    public int getBribeAmount() {
        int bribe = 0;
        bribe = (int) ((playerCredits * .10) +
                (playerCredits * (.5 * playerItems[firearmsIndex]) * (.01 * difficulty)) +
                (playerCredits * (.5 * playerItems[narcoticsIndex]) * (.01 * difficulty)));
        return bribe;
    }

}
