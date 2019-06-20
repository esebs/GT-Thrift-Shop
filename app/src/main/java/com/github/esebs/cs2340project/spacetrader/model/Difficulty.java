package com.github.esebs.cs2340project.spacetrader.model;
/**
 * All possible difficulties
 * @version 1.0
 * @author Sebastian Escobar
 */
public enum Difficulty {
    BABY ("Baby", 0),
    EASY ("Easy",1),
    MEDIUM ("Medium", 2),
    HARD ("Hard", 3),
    BOB ("Bob Waters", 4);

    private String diff;
    private int mult;

    /**
     * The different difficulty
     * @param diff String representation of Enum
     * @param mult multiple of how hard the game is
     */
    Difficulty (String diff, int mult) {
        this.diff = diff;
        this.mult = mult;
    }

    @Override
    public String toString() {
        return this.diff;
    }

    /**
     * Returns Multiple
     * @return multiple
     */
    public int getMult() {
        return mult;
    }

    /**
     * Returns string representation of enum
     * @return diff
     */
    public String getDiff() {
    return diff;
    }

}
