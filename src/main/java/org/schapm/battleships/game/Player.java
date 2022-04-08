package org.schapm.battleships.game;

import org.schapm.battleships.domain.GameUnit;

/**
 * @author schapm
 */

public abstract class Player {

    private final String name;
    private final GameUnit gameUnit;

    public Player(String name) {
        this.name = name;
        this.gameUnit = new GameUnit();
    }

    public String getName() {
        return this.name;
    }

    public GameUnit getGameUnit() {
        return this.gameUnit;
    }

}
