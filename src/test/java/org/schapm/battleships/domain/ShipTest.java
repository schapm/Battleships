package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    private final static int EXPECTED_BATTLESHIP_LENGTH = 5;
    private final static int EXPECTED_DESTROYER_LENGTH = 4;

    Battleship battleship;
    Destroyer destroyer;

    @BeforeEach
    public void setupShips() {
        battleship = new Battleship();
        destroyer = new Destroyer();
    }

    @Test
    public void battleshipIsCorrectLengthAndHasCorrectName() {
        assertEquals(EXPECTED_BATTLESHIP_LENGTH, battleship.getLength(), "Battleship should be of length " + EXPECTED_BATTLESHIP_LENGTH + ". Instead, it is " + battleship.getLength());
        assertEquals("battleship", battleship.getName().toLowerCase(), "Battleship name should be 'Battleship'. Instead, it is " + battleship.getName());
    }

    @Test
    public void destroyerIsCorrectLengthAndHasCorrectName() {
        assertEquals(EXPECTED_DESTROYER_LENGTH, destroyer.getLength(), "Destroyer should be of length " + EXPECTED_DESTROYER_LENGTH + ". Instead, it is " + destroyer.getLength());
        assertEquals("destroyer", destroyer.getName().toLowerCase(), "Destroyer name should be 'Destroyer'. Instead, it is " + destroyer.getName());
    }

    @Test
    public void shipsHaveEitherHorizontalOrVerticalOrientation() {
        assertTrue(battleship.getOrientation().equals(Ship.Orientation.HORIZONTAL) || battleship.getOrientation().equals(Ship.Orientation.VERTICAL), battleship.getName() + " doesn't have a horizontal or vertical orientation");
        assertTrue(destroyer.getOrientation().equals(Ship.Orientation.HORIZONTAL) || destroyer.getOrientation().equals(Ship.Orientation.VERTICAL), destroyer.getName() + " doesn't have a horizontal or vertical orientation");
    }

    @Test
    public void shipsHaveCorrectInitial() {
        assertEquals("B", String.valueOf(battleship.getNameInitial()), battleship.getName() + " does not have the correct initial of 'B'");
        assertEquals("D", String.valueOf(destroyer.getNameInitial()), destroyer.getName() + " does not have the correct initial of 'D'");
    }

}