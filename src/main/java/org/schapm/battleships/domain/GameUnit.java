package org.schapm.battleships.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author schapm
 */

public class GameUnit {

    public final static int OCEAN_SIZE = 10;
    public final static int NUM_OF_BATTLESHIPS = 2;
    public final static int NUM_OF_DESTROYERS = 1;

    private final Coordinate[][] ocean;
    private final Coordinate[][] guesses;

    private final ArrayList<Ship> ships;

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
            Battleship battleship = new Battleship();
            ships.add(battleship);
        }

        for (int i = 0; i < NUM_OF_DESTROYERS; i++) {
            Destroyer destroyer = new Destroyer();
            ships.add(destroyer);
        }
    }

    private void setShipCoordinates() throws NullPointerException {
        Random random = new Random();
        int randomRowOrColumn;
        int randomWithinRowOrColumn;

        for (Ship ship : ships) {
            Coordinate[] coordinates = new Coordinate[ship.getLength()];

            randomRowOrColumn = random.nextInt(OCEAN_SIZE);
            randomWithinRowOrColumn = random.nextInt(OCEAN_SIZE);

            while (randomRowOrColumn + ship.getLength() > OCEAN_SIZE
                    || randomWithinRowOrColumn + ship.getLength() > OCEAN_SIZE) {
                randomRowOrColumn = random.nextInt(OCEAN_SIZE);
                randomWithinRowOrColumn = random.nextInt(OCEAN_SIZE);
            }

            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getOrientation().equals(Ship.Orientation.HORIZONTAL)) {
                    coordinates[i] = ocean[randomRowOrColumn][randomWithinRowOrColumn + i];
                }

                if (ship.getOrientation().equals(Ship.Orientation.VERTICAL)) {
                    coordinates[i] = ocean[randomRowOrColumn + i][randomWithinRowOrColumn];
                }
            }

            ship.addCoordinates(coordinates);
        }
    }

    private void setupGrid(Coordinate[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Coordinate(row, col);
            }
        }
    }

    public Coordinate[][] getOcean() {
        return this.ocean;
    }

    public Coordinate[][] getGuesses() {
        return this.guesses;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

}
