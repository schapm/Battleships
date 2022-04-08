package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameUnitTest {

    private final static int EXPECTED_OCEAN_SIZE = 10;

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

}