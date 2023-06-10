package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.board.Space;
import edu.cmu.cs214.hw3.board.Board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MinotaurTest {
    private Player player;
    private Player player1;
    private Minotaur worker;
    private NormalWorker worker1;
    private Space space;
    private Space space1;
    private Space space2;
    private Board board;

    @Before
    public void setUp() {
        player = new Player(1);
        player1 = new Player(2);
        space = new Space(2, 2);
        space1 = new Space(2, 3);
        space2 = new Space(2, 4);
        worker = new Minotaur(space, 1);
        board = new Board(5, 5);
        worker1 = new NormalWorker(space1, 2);
    }

    @Test
    public void testGetWorkerLocation() {
        assertEquals(space.getRow(), worker.getWorkerX());
        assertEquals(space.getCol(), worker.getWorkerY());
    }

    public void testGetWin() {
        assertFalse(worker.getWin());
    }

    public void testBuiltDone() {
        assertTrue(worker.builtDone());
    }

    public void testIsNeighbor() {
        assertTrue(worker.isNeighbor(space1));
    }

    public void testIsNeighbor1() {
        assertFalse(worker.isNeighbor(space2));
    }

    public void testMoveWorkerLocation() {
        assertTrue(worker.moveWorkerLocation(space1, board));
        assertEquals(space1.getRow(), worker.getWorkerX());
        assertEquals(space1.getCol(), worker.getWorkerY());
        assertEquals(space2.getRow(), worker1.getWorkerX());
        assertEquals(space2.getCol(), worker1.getWorkerY());
    }

    public void testSetWin() {
        worker.setWin();
        assertTrue(worker.getWin());
    }

}
