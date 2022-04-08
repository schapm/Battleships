package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class GameUnit {

    public final static int OCEAN_SIZE = 10;

    private final Coordinate[] ocean;
    private final Coordinate[] guesses;

    public GameUnit() {
        this.ocean = new Coordinate[OCEAN_SIZE];
        this.guesses = new Coordinate[OCEAN_SIZE];
    }

    public Coordinate[] getOcean() {
        return this.ocean;
    }

    public Coordinate[] getGuesses() {
        return this.guesses;
    }

}
