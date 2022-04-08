package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class GameUnit {

    private final Coordinate ocean;
    private final Coordinate guesses;

    public GameUnit() {
        this.ocean = new Coordinate();
        this.guesses = new Coordinate();
    }

    public Coordinate getOcean() {
        return this.ocean;
    }

    public Coordinate getGuesses() {
        return this.guesses;
    }

}
