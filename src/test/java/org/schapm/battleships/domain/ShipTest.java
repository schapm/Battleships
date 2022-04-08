package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    private final static int EXPECTED_BATTLESHIP_LENGTH = 5;

    Battleship battleship;

    @BeforeEach
    public void setupShips() {
        battleship = new Battleship();
    }

    @Test
    public void battleshipIsCorrectLengthAndHasCorrectName() {
        assertEquals(EXPECTED_BATTLESHIP_LENGTH, battleship.getLength(), "Battleship should be of length " + EXPECTED_BATTLESHIP_LENGTH + ". Instead, it is " + battleship.getLength());
        assertEquals("battleship", battleship.getName().toLowerCase(), "Battleship name should be 'Battleship'. Instead, it is " + battleship.getName());
    }

}