package com.github.esebs.cs2340project.spacetrader.entities;
import com.github.esebs.cs2340project.spacetrader.model.Model;

import java.util.Random;

public class AlbinoSquirrel {
    private Model model;
    private Player player;
    Random rand = new Random();
    int die = rand.nextInt(2);

    public AlbinoSquirrel(){
        model = Model.getModelInstance();
        player = model.getPlayer();
    }

    public Player doesKill(int die) {
         if (die == 0) {
             player.setHealth(0);
             return player;
         } else {
             return player;
         }
    }

}
