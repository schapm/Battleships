package org.schapm.battleships.game;

import org.schapm.battleships.domain.Coordinate;
import org.schapm.battleships.domain.GameUnit;

import java.util.Random;

/**
 * @author schapm
 */

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    public Coordinate guess() {
        Random random = new Random();
        int x = random.nextInt(GameUnit.OCEAN_SIZE);
        int y = random.nextInt(GameUnit.OCEAN_SIZE);

        return new Coordinate(x, y);
    }

}
