package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StructuralTest {
    Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void structuralTest() {
        game = game.setWorkerType("Minotaur");
        game = game.play(1, 2);
        game = game.play(2, 3);
        game = game.setWorkerType("Normal");
        game = game.play(3, 2);
        game = game.play(2, 1);
        game = game.play(2, 3);
        game = game.play(3, 3);
        game = game.play(2, 3);
        game = game.play(3, 2);
        game = game.play(2, 2);
        game = game.play(2, 3);
        game = game.play(2, 3);
        game = game.play(1, 2);
        game = game.play(0, 3);
        game = game.play(1, 3);
        game = game.play(2, 2);
        game = game.play(1, 3);
        game = game.play(2, 3);
        game = game.play(0, 3);
        game = game.play(1, 2);
        game = game.play(2, 2);
        game = game.play(1, 3);
        game = game.play(0, 3);
        game = game.play(1, 3);
        game = game.play(1, 2);
        game = game.play(2, 2);
        game = game.play(3, 2);
        game = game.play(0, 3);
        game = game.play(1, 4);
        game = game.play(2, 4);
        game = game.play(2, 2);
        game = game.play(1, 3);
        game = game.play(0, 3);
        game = game.play(1, 4);
        game = game.play(2, 4);
        game = game.play(3, 4);
        game = game.play(1, 3);
        game = game.play(2, 3);
        assertEquals(game.getPlayer1(), game.getWinner());
    }
}
