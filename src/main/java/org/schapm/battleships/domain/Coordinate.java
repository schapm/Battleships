package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class Coordinate {

    public final static String HIT = "X";
    public final static String MISS = "O";
    public final static String EMPTY = " ";

    private String value;

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = EMPTY;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String state) {
        this.value = state;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
