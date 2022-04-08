package org.schapm.battleships.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author schapm
 */

public abstract class Ship {

    private final String name;
    private final int length;
    private final Orientation orientation;
    private final ArrayList<Coordinate> coordinates;

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.orientation = randomOrientation();
        this.coordinates = new ArrayList<>();
    }

    private Orientation randomOrientation() {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand == 0) {
            return Orientation.HORIZONTAL;
        } else {
            return Orientation.VERTICAL;
        }
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

    public ArrayList<Coordinate> getCoordinates() {
        return this.coordinates;
    }
}
