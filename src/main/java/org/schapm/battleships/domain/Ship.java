package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public abstract class Ship {

    private final String name;
    private final int length;
    private final Orientation orientation;

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.orientation = Orientation.HORIZONTAL;
    }

    public String getName() {
        return this.name;
    }

    public char getNameInitial() {
        return this.name.charAt(0);
    }

    public int getLength() {
        return this.length;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

}
