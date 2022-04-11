package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.GameUnit;

import java.util.Objects;

import static org.schapm.battleships.domain.Coordinate.*;

/**
 * @author schapm
 */

public abstract class Player {

    private final String name;
    private final GameUnit gameUnit;
    private Player opponent;

    public Player(String name) {
        this.name = name;
        this.gameUnit = new GameUnit();
        this.opponent = null;
    }

    public String getName() {
        return this.name;
    }

    public GameUnit getGameUnit() {
        return this.gameUnit;
    }

    public Player getOpponent() {
        return Objects.requireNonNullElseGet(opponent, () -> new ComputerPlayer("ComputerPlayer"));
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public String guessOutcome(Coordinate coordinate) {
        if (coordinate.hasShip()) {
            getGameUnit().getGuesses()[coordinate.getX()][coordinate.getY()].setValue(HIT);
            getOpponent().getGameUnit().getOcean()[coordinate.getX()][coordinate.getY()].setValue(HIT);
            getOpponent().getGameUnit().getOcean()[coordinate.getX()][coordinate.getY()].getShip().removeCoordinate(coordinate);

            return HIT;
        }

        return Coordinate.MISS;
    }

}
