package org.schapm.battleships.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameUnitTest {

    private final static int EXPECTED_OCEAN_SIZE = 10;
    private final static int EXPECTED_SHIPS_SIZE = 3;
    private final static int EXPECTED_NUM_OF_BATTLESHIPS = 1;
    private final static int EXPECTED_NUM_OF_DESTROYERS = 2;

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
        assertNotNull(gameUnit.getOcean()[EXPECTED_OCEAN_SIZE - 1][EXPECTED_OCEAN_SIZE - 1]);

        assertNotNull(gameUnit.getGuesses()[0][0]);
        assertNotNull(gameUnit.getGuesses()[EXPECTED_OCEAN_SIZE - 1][EXPECTED_OCEAN_SIZE - 1]);
    }

    @Test
    public void gameUnitCreatesCorrectNumberOfShips() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();
        assertEquals(EXPECTED_SHIPS_SIZE, ships.size(), "Expected a size of " + EXPECTED_SHIPS_SIZE + " for ships. Instead, it is " + ships.size());
    }

    @Test
    public void gameUnitCreatesCorrectTypesOfShips() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();

        int battleships = 0;
        int destroyers = 0;

        for (ShipInterface ship : ships) {
            if (ship.getLength() == 5) {
                battleships++;
            }

            if (ship.getLength() == 4) {
                destroyers++;
            }
        }

        assertEquals(EXPECTED_NUM_OF_BATTLESHIPS, battleships, "Expected " + EXPECTED_NUM_OF_BATTLESHIPS + " battleships. Instead, it is " + battleships);
        assertEquals(EXPECTED_NUM_OF_DESTROYERS, destroyers, "Expected " + EXPECTED_NUM_OF_DESTROYERS + " destroyers. Instead, it is " + destroyers);
    }

    @Test
    public void gameUnitAssignsCreatedShipsWithCoordinate() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();

        for (ShipInterface ship : ships) {
            assertNotEquals(0, ship.getCoordinates().size(), "Coordinates for ship should not have a size of zero");
        }
    }

    @Test
    public void gameUnitAssignsCreatedShipsWithMultipleCoordinates() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();

        for (ShipInterface ship : ships) {
            assertTrue(ship.getCoordinates().size() > 1, "Ship should have multiple coordinates");
        }
    }

    @Test
    public void gameUnitAssignsCreatedShipsWithRandomGridCoordinates() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();
        ArrayList<Coordinate> shipsCoordinates = new ArrayList<>();

        GameUnit secondGameUnit = new GameUnit();
        ArrayList<ShipInterface> secondShips = secondGameUnit.getShips();
        ArrayList<Coordinate> secondShipsCoordinates = new ArrayList<>();

        for (ShipInterface ship : ships) {
            shipsCoordinates.addAll(ship.getCoordinates());
        }

        for (ShipInterface ship : secondShips) {
            secondShipsCoordinates.addAll(ship.getCoordinates());
        }

        assertNotEquals(secondShipsCoordinates, shipsCoordinates, "The second GameUnit ships coordinates are the same as the first GameUnit ship coordinates");
    }

    @Test
    public void gameUnitAssignsCreatedShipsWithRandomSequentialGridCoordinatesWithinOceanLimits() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();
        ArrayList<Coordinate> shipsCoordinates;

        for (ShipInterface ship : ships) {
            shipsCoordinates = ship.getCoordinates();

            // First coordinates
            int x = shipsCoordinates.get(0).getX();
            int y = shipsCoordinates.get(0).getY();

            for (int i = 0; i < ship.getLength(); i++) {
                assertTrue(shipsCoordinates.get(i).getX() == x + i
                        || shipsCoordinates.get(i).getY() == y + i, "The X or Y value has not incremented by 1, i.e. it is not sequential");
            }
        }
    }

    @Test
    public void gameUnitSetsShipsCoordinateValuesToShipInitial() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();

        for (ShipInterface ship : ships) {
            ship.getCoordinates().forEach(coordinate -> assertEquals(String.valueOf(ship.getNameInitial()), coordinate.getValue(), "Coordinate value is not set to the ship's initial"));
        }
    }

    @Test
    public void gameUnitSetsAssignedShipCoordinatesToShip() {
        ArrayList<ShipInterface> ships = gameUnit.getShips();

        for (ShipInterface ship : ships) {
            ship.getCoordinates().forEach(coordinate -> assertTrue(coordinate.hasShip(), "Coordinate does not have a ship"));
            ship.getCoordinates().forEach(coordinate -> assertSame(ship, coordinate.getShip(), "Coordinate's ship is not the same as the ship with the assigned coordinate"));
        }
    }

    @Test
    public void gameUnitShipsArentPlacedOverExistingShips() {
        ArrayList<Coordinate> allCoordinates = new ArrayList<>();
        Set<Coordinate> allCoordinatesAsSet = new HashSet<>();

        for (int i = 0; i < 20; i++) {
            gameUnit = new GameUnit();

            for (ShipInterface ship : gameUnit.getShips()) {
                allCoordinates.addAll(ship.getCoordinates());
                allCoordinatesAsSet.addAll(ship.getCoordinates());
            }
        }

        assertEquals(allCoordinatesAsSet.size(), allCoordinates.size(), "Duplicate coordinates were found in different ships");
    }

}