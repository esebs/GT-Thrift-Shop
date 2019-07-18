package com.github.esebs.cs2340project.spacetrader.entities;

import com.github.esebs.cs2340project.spacetrader.model.Model;

public class BobWaters {
    private Model model;
    private Player player;
    private double random;

    public BobWaters() {
        model = Model.getModelInstance();
        player = model.getPlayer();
    }

    /**
     * Given a random double [0, 1), if the number is greater
     * than or equal to 0.5, then Bob gives you 10,000 credits
     * otherwise Bob does nothing.
     *
     * @return true if Bob gives the player money, else false
     */
    public boolean givesPlayerMoney() {
        random = Math.random();
        if (random >= 0.5) {
            player.setCredits(player.getCredits() + 10000);
            return true;
        } else {
            return false;
        }

    }
}
