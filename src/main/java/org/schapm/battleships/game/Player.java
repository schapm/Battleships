package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.GameUnit;
import org.schapm.battleships.domain.ShipInterface;

import static org.schapm.battleships.domain.Coordinate.*;

/**
 * @author schapm
 */

public abstract class Player implements PlayerInterface {

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

    public Player getPlayer() {
        return this;
    }

    public GameUnit getGameUnit() {
        return this.gameUnit;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public String guessOutcome(Coordinate coordinate) {
        Coordinate opponentOceanCoordinate = getOpponent().getGameUnit().getOcean()[coordinate.getX()][coordinate.getY()];
        Coordinate playerGuessesCoordinate = getGameUnit().getGuesses()[coordinate.getX()][coordinate.getY()];

        if (coordinate.hasShip()) {
            ShipInterface ship = coordinate.getShip();
            playerGuessesCoordinate.setValue(HIT);

            opponentOceanCoordinate.setValue(HIT);
            opponentOceanCoordinate.getShip().removeCoordinate(coordinate);

            if (ship.isShipSunk()) {
                getOpponent().getGameUnit().getShips().removeIf(ship::equals);
            }

            return HIT;
        }

        playerGuessesCoordinate.setValue(MISS);
        opponentOceanCoordinate.setValue(MISS);

        return Coordinate.MISS;
    }

}
