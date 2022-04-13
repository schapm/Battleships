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

}