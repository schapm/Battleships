package org.schapm.battleships.domain;

import java.util.ArrayList;

public interface ShipInterface {

    Ship getShip();
    String getName();
    char getNameInitial();
    int getLength();
    Ship.Orientation getOrientation();
    ArrayList<Coordinate> getCoordinates();
    void addCoordinate(Coordinate coordinate);
    void addCoordinates(Coordinate[] coordinates);
    void removeCoordinate(Coordinate coordinateToRemove);
    boolean isShipSunk();

}