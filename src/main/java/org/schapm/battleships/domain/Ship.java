package org.schapm.battleships.domain;

import java.util.*;

/**
 * @author schapm
 */

public class Ship implements ShipInterface {

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

    public void addCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public void addCoordinates(Coordinate[] coordinates) {
        this.coordinates.addAll(Arrays.asList(coordinates));
    }

    public void removeCoordinate(Coordinate coordinateToRemove) {
        this.coordinates.removeIf(coordinate -> coordinate.equals(coordinateToRemove));
    }

    public boolean isShipSunk() {
        return this.coordinates.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return length == ship.length && Objects.equals(name, ship.name) && orientation == ship.orientation && Objects.equals(coordinates, ship.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, orientation, coordinates);
    }

}
