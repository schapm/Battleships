package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.GameUnit;

public interface PlayerInterface {

    Player getPlayer();
    Player getOpponent();
    void setOpponent(Player player);
    GameUnit getGameUnit();
    String getName();
    String guessOutcome(Coordinate coordinate);
}
