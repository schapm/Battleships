package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;

import static org.schapm.battleships.domain.GameUnit.OCEAN_SIZE;

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
        String padding = "  ";
        for (int row = 0; row < grid.length; row++) {
            if (row == 0) {
                System.out.print(padding);
                for (int i = 1; i < OCEAN_SIZE + 1; i++) {
                    System.out.print(padding + i);
                }
            }
            System.out.println();
            for (int col = 0; col < grid.length; col++) {
                System.out.print("[" + grid[row][col].getValue() + "]");
            }
        }
    }

    public int getNumberFromLetter(char letter) {
        int letterToReturn = Character.toLowerCase(letter) - 'a' + 1;

        return letterToReturn > 0 && letterToReturn < 27 ? letterToReturn : -1;
    }

    public char getLetterFromNumber(int number) {
        return number > 0 && number < 27 ? (char) (number + 96) : 1;
    }

}
