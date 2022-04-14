package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;

import java.util.Scanner;

import static org.schapm.battleships.domain.Coordinate.*;
import static org.schapm.battleships.domain.GameUnit.OCEAN_SIZE;

/**
 * @author schapm
 */

public class Game {

    protected final HumanPlayer player;
    protected final ComputerPlayer opponent;
    protected final Scanner scanner;

    public Game() {
        this.player = new HumanPlayer("Player");
        this.opponent = new ComputerPlayer("Computer");
        this.player.setOpponent(opponent);
        this.opponent.setOpponent(player);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (!isWinner()) {
            playerTurn();
            if (isWinner()) {
                break;
            }
            //opponentTurn();
        }
    }

    protected void playerTurn() {
        printGrid(player.getGameUnit().getGuesses());
        printGrid(player.getGameUnit().getOcean());

        System.out.println("What's your guess?");
        System.out.print("> ");

        String guess = scanner.nextLine();
        if (!validateUserInput(guess)) {
            while (true) {
                System.out.println("Guess should be between A-J & 0-10, e.g. \"J10\"");
                System.out.print("> ");

                String newGuess = scanner.nextLine();
                if (validateUserInput(newGuess)) {
                    guess = newGuess;
                    break;
                }
            }
        }

        Coordinate guessAsCoordinate = playerGuessToCoordinate(guess);
        String guessOutcome = player.guessOutcome(guessAsCoordinate);

        System.out.println(guessOutcome);
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
                if (col == 0) {
                    System.out.print(Character.toUpperCase(getLetterFromNumber(row + 1)) + padding);
                }
                System.out.print("[" + grid[row][col].getValue() + "]");
            }
        }

        System.out.println();
    }

    public String printGuessOutcome(String outcome, Coordinate guessCoordinate) {
        String userFriendlyOutcome = "";

        if (outcome.equals(HIT)) {
            userFriendlyOutcome = "HIT: " + guessCoordinate.getShip().getName().toUpperCase();
        }

        if (outcome.equals(MISS)) {
            userFriendlyOutcome = "MISS";
        }

        return userFriendlyOutcome;
    }

    public int getNumberFromLetter(char letter) {
        int letterToReturn = Character.toLowerCase(letter) - 'a' + 1;

        return letterToReturn > 0 && letterToReturn < 27 ? letterToReturn : -1;
    }

    public char getLetterFromNumber(int number) {
        return number > 0 && number < 27 ? (char) (number + 96) : 1;
    }

    public boolean validateUserInput(String guess) {
        String firstIsLetter = "[a-jA-J]";
        String secondIsNumberOneToTen = "(10|[1-9])$";
        String regex = firstIsLetter + secondIsNumberOneToTen;

        return guess.matches(regex);
    }

    public Coordinate playerGuessToCoordinate(String guess) {
        String[] split = guess.split("");
        int letterAsXValue = getNumberFromLetter(split[0].toLowerCase().charAt(0)) - 1;
        int numberAsYValue;

        if (guess.length() == 3) {
            numberAsYValue = Integer.parseInt(split[1] + split[2]) - 1;
        } else {
            numberAsYValue = Integer.parseInt(split[1]) - 1;
        }

        return player.getOpponent().getGameUnit().getOcean()[letterAsXValue][numberAsYValue];
    }

}
