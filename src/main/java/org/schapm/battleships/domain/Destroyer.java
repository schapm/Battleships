package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class Destroyer extends Ship {

    private final static String NAME = "Destroyer";
    private final static int LENGTH = 4;

    public Destroyer() {
        super(NAME, LENGTH);
    }
}
