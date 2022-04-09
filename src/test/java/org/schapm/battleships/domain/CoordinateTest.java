package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    Coordinate coordinate;

    @BeforeEach
    public void setupClass() {
        coordinate = new Coordinate(0, 5);
    }

    @Test
    public void coordinateHasAnXAndYValue() {
        assertEquals(0, coordinate.getX(), "Expected X-value of " + 0 + ". Instead, it is " + coordinate.getX());
        assertEquals(5, coordinate.getY(), "Expected Y-value of " + 5 + ". Instead, it is " + coordinate.getY());
    }

    @Test
    public void coordinateHasADefaultValueOfEmpty() {
        assertEquals(Coordinate.EMPTY, coordinate.getValue(), "Coordinate did not have an 'EMPTY' value when created");
    }

    @Test
    public void coordinateCanHaveAValueOfHitMissOrShipInitial() {
        coordinate.setValue(Coordinate.HIT);
        assertEquals(Coordinate.HIT, coordinate.getValue(), "After setting the coordinate value to 'HIT', it does not have the same value");

        coordinate.setValue(Coordinate.MISS);
        assertEquals(Coordinate.MISS, coordinate.getValue(), "After setting the coordinate value to 'MISS', it does not have the same value");

        Destroyer destroyer = new Destroyer();
        coordinate.setValue(String.valueOf(destroyer.getNameInitial()));
        assertEquals(String.valueOf(destroyer.getNameInitial()), coordinate.getValue(), "After setting the coordinate value to destroyer ship initial 'D', it does not have the same value");
    }

    @Test
    public void coordinateCanHaveAShip() {
        Battleship battleship = new Battleship();
        coordinate.setShip(battleship);

        assertNotNull(coordinate.getShip(), "Coordinate ship should not be null");
        assertSame(battleship, coordinate.getShip(), "Ship is not the same as the ship set on the coordinate");
    }

    @Test
    public void coordinateReturnsStatusOfShip() {
        assertFalse(coordinate.hasShip(), "Coordinate should return false when no ship has been set");
        
        coordinate.setShip(new Battleship());
        assertTrue(coordinate.hasShip(), "Coordinate should return true after the ship has been set");
    }

}