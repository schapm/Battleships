package org.schapm.battleships.domain;

/**
 * @author schapm
 */

public class GameUnit {

    public final static int OCEAN_SIZE = 10;

    private final Coordinate[][] ocean;
    private final Coordinate[][] guesses;

    public GameUnit() {
        this.ocean = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        this.guesses = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        setupGrid(ocean);
        setupGrid(guesses);
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

}
