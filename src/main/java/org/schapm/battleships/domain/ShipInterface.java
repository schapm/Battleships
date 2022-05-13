package org.schapm.battleships.domain;

import java.util.ArrayList;

public interface ShipInterface {

    String getName();
    char getNameInitial();
    int getLength();
    Ship.Orientation getOrientation();
    ArrayList<Coordinate> getCoordinates();
    void addCoordinates(Coordinate[] coordinates);
    void removeCoordinate(Coordinate coordinateToRemove);
    boolean isShipSunk();

}