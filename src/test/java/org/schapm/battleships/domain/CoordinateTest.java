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

}