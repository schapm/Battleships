package org.schapm.battleships.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.schapm.battleships.domain.GameUnit.OCEAN_SIZE;

public class GameTest {

    private Game game;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String regexToMatchCoordinateOutput = "\\[(.*?)\\]";

    @BeforeEach
    public void setUp() {
        game = new Game();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void gameHasPlayerAndOpponent() {
        assertNotNull(game.player, "Player object should not be null");
        assertTrue(game.player instanceof HumanPlayer, game.player.getName() + " is not an instance of class 'HumanPlayer'");

        assertNotNull(game.opponent, "Opponent object should not be null");
        assertTrue(game.opponent instanceof ComputerPlayer, game.opponent.getName() + " is not an instance of class 'ComputerPlayer'");
    }

    @Test
    public void gamePlayersHaveOpponentSet() {
        assertNotNull(game.player.getOpponent(), "Player must have an opponent set");
        assertSame(game.opponent, game.player.getOpponent(), "Player's set opponent is not the same as opponent");

        assertNotNull(game.opponent.getOpponent(), "Opponent must have an opponent set");
        assertSame(game.player, game.opponent.getOpponent(), "Opponent's set opponent is not the same as player");
    }

    @Test
    public void gameMethodIsWinnerReturnsFalseOnInstantiation() {
        assertFalse(game.isWinner(), "isWinner() should return false on start");
    }

    @Test
    public void isWinnerReturnsTrueIfEitherPlayersShipListIsEmpty() {
        game.player.getGameUnit().getShips().clear();
        assertTrue(game.isWinner(), "isWinner() should return true, as player's ship list was cleared");

        game = new Game();
        game.opponent.getGameUnit().getShips().clear();
        assertTrue(game.isWinner(), "isWinner() should return true, as opponent's ship list was cleared");
    }

    @Test
    public void playerGridsCanBePrinted() {
        game.printGrid(game.player.getGameUnit().getOcean());

        Pattern pattern = Pattern.compile(regexToMatchCoordinateOutput);
        Matcher matcher = pattern.matcher(outContent.toString());

        int coordinateOccurrences = 0;
        while (matcher.find()) {
            coordinateOccurrences++;
        }

        assertEquals(OCEAN_SIZE * OCEAN_SIZE, coordinateOccurrences, "Number of coordinates does not match the output. Expected " +
                OCEAN_SIZE * OCEAN_SIZE + ". Instead, it is " + coordinateOccurrences);
    }

    @Test
    public void letterCanBeConvertedToNumber() {
        assertEquals(1, game.getNumberFromLetter('A'));
        assertEquals(1, game.getNumberFromLetter('a'));
        assertEquals(13, game.getNumberFromLetter('M'));
        assertEquals(26, game.getNumberFromLetter('Z'));
        assertEquals(26, game.getNumberFromLetter('z'));
        assertEquals(-1, game.getNumberFromLetter('3'));
    }

}