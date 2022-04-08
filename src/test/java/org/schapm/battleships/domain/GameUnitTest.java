package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameUnitTest {

    private final static int EXPECTED_OCEAN_SIZE = 10;
    private final static int EXPECTED_SHIPS_SIZE = 3;
    private final static int EXPECTED_NUM_OF_BATTLESHIPS = 2;
    private final static int EXPECTED_NUM_OF_DESTROYERS = 1;

    GameUnit gameUnit;

    @BeforeEach
    public void setupClass() {
        gameUnit = new GameUnit();
    }

    @Test
    public void gameUnitPlayerOceanAndPlayerGuessesGridsExist() {
        assertNotNull(gameUnit.getOcean(), "Ocean grid should not be null");
        assertNotNull(gameUnit.getGuesses(), "Guesses grid should not be null");

        assertNotEquals(gameUnit.getGuesses(), gameUnit.getOcean(), "getGuesses() and getOcean() should not be the same");
    }

    @Test
    public void gameUnitOceanAndGuessesGridsAreTheCorrectSize() {
        assertEquals(EXPECTED_OCEAN_SIZE, gameUnit.getOcean().length, "Ocean grid should be of length " + EXPECTED_OCEAN_SIZE + ". Instead, it is " + gameUnit.getOcean().length);
        assertEquals(EXPECTED_OCEAN_SIZE, gameUnit.getGuesses().length, "Guesses grid should be of length " + EXPECTED_OCEAN_SIZE + ". Instead, it is " + gameUnit.getGuesses().length);
    }

    @Test
    public void gameUnitOceanAndGuessesGridsAreMultiDimensionalAndNotNull() {
        assertNotNull(gameUnit.getOcean()[0][0]);
        assertNotNull(gameUnit.getOcean()[EXPECTED_OCEAN_SIZE -1 ][EXPECTED_OCEAN_SIZE - 1]);

        assertNotNull(gameUnit.getGuesses()[0][0]);
        assertNotNull(gameUnit.getGuesses()[EXPECTED_OCEAN_SIZE -1 ][EXPECTED_OCEAN_SIZE - 1]);
    }

    @Test
    public void gameUnitCreatesCorrectNumberOfShips() {
        ArrayList<Ship> ships = gameUnit.getShips();
        assertEquals(EXPECTED_SHIPS_SIZE, ships.size(), "Expected a size of " + EXPECTED_SHIPS_SIZE + " for ships. Instead, it is " + ships.size());
    }

    @Test
    public void gameUnitCreatesCorrectTypesOfShips() {
        ArrayList<Ship> ships = gameUnit.getShips();

        int battleships = 0;
        int destroyers = 0;

        for (Ship ship : ships) {
            if (ship instanceof Battleship) {
                battleships++;
            }

            if (ship instanceof Destroyer) {
                destroyers++;
            }
        }

        assertEquals(EXPECTED_NUM_OF_BATTLESHIPS, battleships, "Expected " + EXPECTED_NUM_OF_BATTLESHIPS + " battleships. Instead, it is " + battleships);
        assertEquals(EXPECTED_NUM_OF_DESTROYERS, destroyers, "Expected " + EXPECTED_NUM_OF_DESTROYERS + " destroyers. Instead, it is " + destroyers);
    }

}