package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class Battleship extends Ship {

    private final static String NAME = "Battleship";
    private final static int LENGTH = 5;

    public Battleship() {
        super(NAME, LENGTH);
    }
}
