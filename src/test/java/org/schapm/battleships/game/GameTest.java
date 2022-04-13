package org.schapm.battleships.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void gameHasPlayerAndOpponent() {
        assertNotNull(game.player, "Player object should not be null");
        assertTrue(game.player instanceof HumanPlayer, game.player.getName() + " is not an instance of class 'HumanPlayer'");

        assertNotNull(game.opponent, "Opponent object should not be null");
        assertTrue(game.opponent instanceof ComputerPlayer, game.opponent.getName() + " is not an instance of class 'ComputerPlayer'");
    }

}