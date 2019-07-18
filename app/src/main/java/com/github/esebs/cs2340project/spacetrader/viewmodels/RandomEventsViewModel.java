package com.github.esebs.cs2340project.spacetrader.viewmodels;

import com.github.esebs.cs2340project.spacetrader.entities.Encounterable;
import com.github.esebs.cs2340project.spacetrader.entities.Trader;
import com.github.esebs.cs2340project.spacetrader.entities.Police;
import com.github.esebs.cs2340project.spacetrader.entities.Pirate;
import com.github.esebs.cs2340project.spacetrader.entities.AlbinoSquirrel;
import com.github.esebs.cs2340project.spacetrader.entities.BobWaters;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * ViewModel for random events (just travel encounters for now)
 */
public class RandomEventsViewModel {

    private Queue<Encounterable> encounters = new LinkedList<>();

    /**
     * Generates a new encounters queue for the ViewModel
     */
    public void createNewEncountersQueue() {
        Queue<Encounterable> newEncounters = new LinkedList<>();

        int maxNumEncounters = 5;
        Random random = new Random();
        int numEncounters = random.nextInt(maxNumEncounters);

        for (int i = 0; i < numEncounters; i++) {
            double randomChance = Math.random();
            if (randomChance <= 0.32) {
                newEncounters.add(new Trader());
            } else if (randomChance <= 0.64) {
                newEncounters.add(new Police());
            } else if (randomChance <= 0.96) {
                newEncounters.add(new Pirate());
            } else if (randomChance <= 0.98) {
                newEncounters.add(new AlbinoSquirrel());
            } else {
                newEncounters.add(new BobWaters());
            }
        }
        this.encounters = newEncounters;
    }

    /**
     * Remove and return the next encounter
     *
     * @return the head of the encounters queue, if it isn't empty
     *         (returns null if there are no encounters in the queue)
     */
    public Encounterable pollEncountersQueue() {
        return encounters.poll();
    }

    /**
     * Gets the encounters during this travel
     *
     * @return
     */
    public Queue<Encounterable> getEncountersQueue() {
        return encounters;
    }
}
