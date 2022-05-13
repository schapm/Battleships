package org.schapm.battleships.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author schapm
 */

public class GameUnit {

    public final static int OCEAN_SIZE = 10;
    private final static int NUM_OF_BATTLESHIPS = 1;
    private final static int NUM_OF_DESTROYERS = 2;

    private final static String BATTLESHIP_NAME = "Battleship";
    private final static int BATTLESHIP_LENGTH = 5;

    private final static String DESTROYER_NAME = "Destroyer";
    private final static int DESTROYER_LENGTH = 4;

    private final Coordinate[][] ocean;
    private final Coordinate[][] guesses;

    private final ArrayList<ShipInterface> ships;

    public GameUnit() {
        this.ocean = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        this.guesses = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        setupGrid(ocean);
        setupGrid(guesses);
        this.ships = new ArrayList<>();
        createShips();
        setShipCoordinates();
    }

    private void createShips() {
        for (int i = 0; i < NUM_OF_BATTLESHIPS; i++) {
            Ship battleship = new Ship(BATTLESHIP_NAME, BATTLESHIP_LENGTH);
            ships.add(battleship);
        }

        for (int i = 0; i < NUM_OF_DESTROYERS; i++) {
            Ship destroyer = new Ship(DESTROYER_NAME, DESTROYER_LENGTH);
            ships.add(destroyer);
        }
    }

    private void setShipCoordinates() throws NullPointerException {
        Random random = new Random();
        int randomRowOrColumn;
        int randomWithinRowOrColumn;

        for (ShipInterface ship : ships) {
            Coordinate[] coordinates = new Coordinate[ship.getLength()];

            randomRowOrColumn = random.nextInt(OCEAN_SIZE);
            randomWithinRowOrColumn = random.nextInt(OCEAN_SIZE);


            while (randomRowOrColumn + ship.getLength() > OCEAN_SIZE
                    || randomWithinRowOrColumn + ship.getLength() > OCEAN_SIZE
                    || wouldShipOverlapAnother(ship, randomRowOrColumn, randomWithinRowOrColumn)) {
                randomRowOrColumn = random.nextInt(OCEAN_SIZE);
                randomWithinRowOrColumn = random.nextInt(OCEAN_SIZE);
            }

            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getOrientation().equals(Ship.Orientation.HORIZONTAL)) {
                    coordinates[i] = ocean[randomRowOrColumn + i][randomWithinRowOrColumn];
                }

                if (ship.getOrientation().equals(Ship.Orientation.VERTICAL)) {
                    coordinates[i] = ocean[randomRowOrColumn][randomWithinRowOrColumn + i];
                }
            }

            ship.addCoordinates(coordinates);
            for (Coordinate coordinate : ship.getCoordinates()) {
                coordinate.setValue(ship.getNameInitial());
                coordinate.setShip(ship);
            }
        }
    }

    private void setupGrid(Coordinate[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Coordinate(row, col);
            }
        }
    }

    private boolean wouldShipOverlapAnother(ShipInterface ship, int randomRowOrColumn, int randomWithinRowOrColumn) {
        for (int i = 0; i < ship.getLength(); i++) {
            switch (ship.getOrientation()) {
                case HORIZONTAL:
                    if (ocean[randomRowOrColumn + i][randomWithinRowOrColumn].hasShip()) {
                        return true;
                    }
                    break;
                case VERTICAL:
                    if (ocean[randomRowOrColumn][randomWithinRowOrColumn + i].hasShip()) {
                        return true;
                    }
            }
        }

        return false;
    }

    public Coordinate[][] getOcean() {
        return this.ocean;
    }

    public Coordinate[][] getGuesses() {
        return this.guesses;
    }

    public ArrayList<ShipInterface> getShips() {
        return this.ships;
    }

}
