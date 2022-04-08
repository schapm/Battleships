package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameUnitTest {

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

}