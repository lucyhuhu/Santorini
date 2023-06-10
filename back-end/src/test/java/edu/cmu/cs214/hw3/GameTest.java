package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.board.Space;
import edu.cmu.cs214.hw3.player.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameTest {
    private Game game;
    private Game selectedGame;
    private Game placedGame;
    private Game movedGame;
    private Game builtGame;
    private Game game1;
    private Player player1;
    private Player player11;
    private Player player12;
    private Space space;

    @Before
    public void setUp() {
        game = new Game();
        game1 = new Game();
        space = game.getBoard().getSpace(0, 0);
        space.changeBuilding();
        space.changeBuilding();
        space.changeBuilding();
        player1 = game.getPlayer1();
        player11 = game1.getPlayer1();
        player12 = game1.getPlayer2();

    }

    @Test
    public void testGetCurrentPlayer() {
        assertEquals(player11, game1.getCurrentPlayer());
    }

    public void testnewTurn() {
        game1.newTurn();
        assertEquals(player12, game.getCurrentPlayer());
    }

    public void testPlaceWorker() {
        placedGame = game.placeWorker(0, 1);
        assertTrue(placedGame.getBoard().getSpace(0, 1).isOccupied());
    }

    public void testSelectWorker() {
        selectedGame = placedGame.select(0, 0);
        assertEquals(0, selectedGame.getSelectedWorker().getWorkerX());
        assertEquals(1, selectedGame.getSelectedWorker().getWorkerY());
    }

    public void testMoveWorker() {
        movedGame = selectedGame.move(0, 0);
        assertEquals(0, movedGame.getSelectedWorker().getWorkerX());
        assertEquals(0, movedGame.getSelectedWorker().getWorkerY());
    }

    public void testBuild() {
        builtGame = game1.build(3, 4);
        assertEquals(1, game1.getBoard().getSpace(3, 4).getLevels());
    }

    public void testGameOver() {
        assertFalse(game1.checkGameOver());
    }

    public void testGameOver1() {
        assertTrue(movedGame.checkGameOver());
    }

}
