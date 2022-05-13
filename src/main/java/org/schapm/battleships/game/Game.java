package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.GameUnit;

import java.util.Random;
import java.util.Scanner;

import static org.schapm.battleships.domain.Coordinate.*;
import static org.schapm.battleships.domain.GameUnit.OCEAN_SIZE;

/**
 * @author schapm
 */

public class Game {

    protected final PlayerInterface player;
    protected final PlayerInterface opponent;
    private final Scanner scanner;

    private final String GUESSES_LABEL = "GUESSES (OPPONENT GRID)";
    private final String OCEAN_LABEL = "OCEAN (YOU)";

    public Game() {
        this.player = new Player("Player");
        this.opponent = new Player("Computer");
        this.player.setOpponent(opponent.getPlayer());
        this.opponent.setOpponent(player.getPlayer());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=======================");
        System.out.println("----- BATTLESHIPS -----");
        System.out.println("=======================");
        System.out.println("Welcome, " + player.getName());

        while (!isWinner()) {
            playerTurn();
            if (isWinner()) {
                break;
            }
            opponentTurn();
        }

        gameEnd();
    }

    private void playerTurn() {
        printGrid(player.getGameUnit().getGuesses(), GUESSES_LABEL);
        printGrid(player.getGameUnit().getOcean(), OCEAN_LABEL);

        System.out.println("\n=== " + player.getName().toUpperCase() + " TURN ===");
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

        System.out.println(printGuessOutcome(guessOutcome, guessAsCoordinate));
    }

    private void opponentTurn() {
        System.out.println("\n=== " + opponent.getName().toUpperCase() + " TURN ===");
        System.out.print("> ");

        Coordinate opponentGuessAsCoordinate = computerGuess();
        String guessOutcome = opponent.guessOutcome(opponentGuessAsCoordinate);

        System.out.println(opponentGuessToUserFriendlyGuess(opponentGuessAsCoordinate));
        System.out.println(printGuessOutcome(guessOutcome, opponentGuessAsCoordinate));
    }

    public boolean isWinner() {
        return player.getGameUnit().getShips().isEmpty()
                || opponent.getGameUnit().getShips().isEmpty();
    }

    public void gameEnd() {
        printGrid(player.getGameUnit().getGuesses(), GUESSES_LABEL);
        printGrid(player.getGameUnit().getOcean(), OCEAN_LABEL);

        if (opponent.getGameUnit().getShips().isEmpty()) {
            System.out.println("\n" + player.getName().toUpperCase() + " WINS!");
        } else {
            System.out.println("\n" + opponent.getName().toUpperCase() + " WINS!");
        }
    }

    public void printGrid(Coordinate[][] grid, String label) {
        System.out.println("---------------------------------");
        System.out.println(label);
        System.out.println("---------------------------------");

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
        StringBuilder userFriendlyOutcome = new StringBuilder();

        if (outcome.equals(HIT)) {
            userFriendlyOutcome.append("HIT: ")
                    .append(guessCoordinate.getShip().getName().toUpperCase());
            if (guessCoordinate.getShip().isShipSunk()) {
                userFriendlyOutcome.append(" SUNK!");
            }
        }

        if (outcome.equals(MISS)) {
            userFriendlyOutcome.append("MISS");
        }

        return userFriendlyOutcome.toString();
    }

    public int getNumberFromLetter(char letter) {
        int letterToReturn = Character.toLowerCase(letter) - 'a' + 1;

        return letterToReturn > 0 && letterToReturn < 27 ? letterToReturn : -1;
    }

    public char getLetterFromNumber(int number) {
        return number > 0 && number < 27 ? (char) (number + 96) : 1;
    }

    public String opponentGuessToUserFriendlyGuess(Coordinate guessCoordinate) {
        String letter = String.valueOf(getLetterFromNumber(guessCoordinate.getX() + 1));
        String number = String.valueOf(guessCoordinate.getY() + 1);

        return (letter + number).toUpperCase();
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

    protected Coordinate computerGuess() {
        Random random = new Random();
        int x = random.nextInt(GameUnit.OCEAN_SIZE);
        int y = random.nextInt(GameUnit.OCEAN_SIZE);

        return opponent.getOpponent().getGameUnit().getOcean()[x][y];
    }

}
