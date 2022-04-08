package org.schapm.battleships.game;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    private final static String HUMAN_PLAYER_NAME = "HumanPlayer";
    private final static String COMPUTER_PLAYER_NAME = "ComputerPlayer";

    @BeforeEach
    public void setupClass() {
        humanPlayer = new HumanPlayer();
        computerPlayer = new ComputerPlayer();
    }

}