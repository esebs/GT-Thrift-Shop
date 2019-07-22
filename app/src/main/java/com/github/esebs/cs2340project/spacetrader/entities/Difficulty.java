package com.github.esebs.cs2340project.spacetrader.entities;
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

    private final String diff;
    private final int multiple;

    /**
     * The different difficulty
     * @param diff String representation of Enum
     * @param multiple multiple of how hard the game is
     */
    Difficulty (String diff, int multiple) {
        this.diff = diff;
        this.multiple = multiple;
    }

    @Override
    public String toString() {
        return this.diff;
    }

    /**
     * Returns Multiple
     * @return multiple
     */
    public int getMultiple() {
        return multiple;
    }
//
//    /**
//     * Returns string representation of enum
//     * @return diff
//     */
//    public String getDiff() {
//    return diff;
//    }

}
