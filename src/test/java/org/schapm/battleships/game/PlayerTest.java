package org.schapm.battleships.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.Destroyer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    private final static String HUMAN_PLAYER_NAME = "HumanPlayer";
    private final static String COMPUTER_PLAYER_NAME = "ComputerPlayer";

    @BeforeEach
    public void setupClass() {
        humanPlayer = new HumanPlayer(HUMAN_PLAYER_NAME);
        computerPlayer = new ComputerPlayer(COMPUTER_PLAYER_NAME);

        humanPlayer.setOpponent(computerPlayer);
        computerPlayer.setOpponent(humanPlayer);
    }

    @Test
    public void playersHaveAName() {
        assertFalse(humanPlayer.getName().isEmpty(), "HumanPlayer name should not be empty");
        assertFalse(computerPlayer.getName().isEmpty(), "ComputerPlayer name should not be empty");

        assertEquals(humanPlayer.getName(), HUMAN_PLAYER_NAME, HUMAN_PLAYER_NAME + " and getName() are not the same");
        assertEquals(computerPlayer.getName(), COMPUTER_PLAYER_NAME, COMPUTER_PLAYER_NAME + " and getName() are not the same");
    }

    @Test
    public void playersHaveOwnGameUnit() {
        assertNotNull(humanPlayer.getGameUnit(), HUMAN_PLAYER_NAME + " GameUnit should not be null");
        assertNotNull(computerPlayer.getGameUnit(), COMPUTER_PLAYER_NAME + " GameUnit should not be null");

        assertNotEquals(humanPlayer.getGameUnit(), computerPlayer.getGameUnit(), HUMAN_PLAYER_NAME + " and " + COMPUTER_PLAYER_NAME + " GameUnit should not be the same");
    }

    @Test
    public void getAndSetPlayersOpponent() {
        ComputerPlayer testComputerPlayer = new ComputerPlayer("TestComputerPlayer");
        HumanPlayer testHumanPlayer = new HumanPlayer("TestHumanPlayer");

        humanPlayer.setOpponent(testComputerPlayer);
        assertSame(testComputerPlayer, humanPlayer.getOpponent(), testComputerPlayer.getName() + " and " + humanPlayer.getName() + " getOpponent() should be the same");
        computerPlayer.setOpponent(testHumanPlayer);
        assertSame(testHumanPlayer, computerPlayer.getOpponent(), testHumanPlayer.getName() + " and " + computerPlayer.getName() + " getOpponent() should be the same");
    }

    @Test
    public void computerPlayerGeneratesRandomGuess() {
        Coordinate firstCoordinate = computerPlayer.guess();
        Coordinate secondCoordinate = computerPlayer.guess();

        assertTrue(firstCoordinate.getX() != secondCoordinate.getX() || firstCoordinate.getY() != secondCoordinate.getY(),
                "Guesses should not be the same. First: x=" + firstCoordinate.getX() + " y=" + firstCoordinate.getY() +
                        "Second: x=" + secondCoordinate.getX() + " y=" + secondCoordinate.getY());
    }

    @Test
    public void computerPlayerGuessCorrespondsToOpponentOceanCoordinate() {
        Coordinate guessCoordinate = computerPlayer.guess();
        Coordinate oceanCoordinate = computerPlayer.getOpponent().getGameUnit().getOcean()[guessCoordinate.getX()][guessCoordinate.getY()];

        assertNotNull(guessCoordinate, COMPUTER_PLAYER_NAME + " coordinate guess should not be null");
        assertNotNull(oceanCoordinate, "Opponent ocean coordinate from " + COMPUTER_PLAYER_NAME + " guess should not null");
        assertSame(oceanCoordinate, guessCoordinate, "Opponent ocean coordinate and guess coordinate should be the same");
    }

    @Test
    public void playerGuessReturnsHitOrMiss() {
        Coordinate coordinate = computerPlayer.guess();
        coordinate.setShip(null);
        assertEquals(Coordinate.MISS, computerPlayer.guessOutcome(coordinate), "No ship set on guess coordinate - " + COMPUTER_PLAYER_NAME + " guess should be equal to the value of 'MISS'");

        coordinate.setShip(new Destroyer());
        assertEquals(Coordinate.HIT, computerPlayer.guessOutcome(coordinate), "Ship set on guess coordinate - " + COMPUTER_PLAYER_NAME + " guess should be equal to the value of 'HIT'");
    }

    @Test
    public void playerGuessRecordsGuessOnOwnGameUnit() {
        Coordinate coordinate = humanPlayer.getOpponent().getGameUnit().getShips().get(0).getCoordinates().get(0); // Get ship coordinate on opponent GameUnit
        humanPlayer.guessOutcome(coordinate);

        assertEquals(Coordinate.HIT, humanPlayer.getGameUnit().getGuesses()[coordinate.getX()][coordinate.getY()].getValue(),
                HUMAN_PLAYER_NAME + " 'HIT' guess does not equal 'HIT' on " + HUMAN_PLAYER_NAME + " guesses grid");
    }

    @Test
    public void playerGuessRecordsGuessOnOpponentGameUnit() {
        Coordinate coordinate = computerPlayer.getOpponent().getGameUnit().getShips().get(0).getCoordinates().get(0); // Get ship coordinate on opponent GameUnit
        computerPlayer.guessOutcome(coordinate);

        assertEquals(Coordinate.HIT, computerPlayer.getOpponent().getGameUnit().getOcean()[coordinate.getX()][coordinate.getY()].getValue(),
                COMPUTER_PLAYER_NAME + " 'HIT' guess does not equal 'HIT' on " + HUMAN_PLAYER_NAME + " ocean grid");
    }

    @Test
    public void playerGuessOfHitOutcomeRemovesCoordinateFromShip() {
        ArrayList<Coordinate> coordinates = computerPlayer.getOpponent().getGameUnit().getShips().get(0).getCoordinates();
        Coordinate coordinate = coordinates.get(0);
        computerPlayer.guessOutcome(coordinate);

        assertFalse(coordinates.contains(coordinate), HUMAN_PLAYER_NAME + " GameUnit ship coordinates still contain the 'HIT' ship coordinates, which should have been removed");
    }

}