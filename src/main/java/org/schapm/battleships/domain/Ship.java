package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public abstract class Ship {

    private final String name;
    private final int length;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }
}
