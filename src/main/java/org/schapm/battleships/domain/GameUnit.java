package org.schapm.battleships.domain;

import java.util.ArrayList;

/**
 * @author schapm
 */

public class GameUnit {

    public final static int OCEAN_SIZE = 10;

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
    }

    private void createShips() {
        for (int i = 0; i < 3; i++) {
            Ship ship = new Destroyer();
            ships.add(ship);
        }
    }

    private void setupGrid(Coordinate[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Coordinate();
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
