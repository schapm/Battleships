package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class Coordinate {

    public final static String HIT = "X";
    public final static String MISS = "O";
    public final static String EMPTY = " ";

    private String value;
    private ShipInterface ship;

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
        switch (state) {
            case HIT:
                this.value = HIT;
                break;
            case MISS:
                this.value = MISS;
                break;
        }
    }

    public void setValue(char shipInitial) {
        this.value = String.valueOf(shipInitial);
    }

    public ShipInterface getShip() {
        return this.ship;
    }

    public void setShip(ShipInterface ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
