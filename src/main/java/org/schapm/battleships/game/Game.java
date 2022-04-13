package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;

/**
 * @author schapm
 */

public class Game {

    protected final HumanPlayer player;
    protected final ComputerPlayer opponent;

    public Game() {
        this.player = new HumanPlayer("Player");
        this.opponent = new ComputerPlayer("Computer");
        this.player.setOpponent(opponent);
        this.opponent.setOpponent(player);
    }

    public boolean isWinner() {
        return player.getGameUnit().getShips().isEmpty()
                || opponent.getGameUnit().getShips().isEmpty();
    }

    public void printGrid(Coordinate[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            System.out.println();
            for (int col = 0; col < grid.length; col++) {
                System.out.print("[" + grid[row][col].getValue() + "]");
            }
        }
    }

}
