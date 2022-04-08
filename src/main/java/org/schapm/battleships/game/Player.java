package org.schapm.battleships.game;

/**
 * @author schapm
 */

public abstract class Player {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
