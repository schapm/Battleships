package org.schapm.battleships;

import org.schapm.battleships.game.Game;

/**
 * @author schapm
 */

public class BattleshipsApplication {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}