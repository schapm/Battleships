package org.schapm.battleships.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}