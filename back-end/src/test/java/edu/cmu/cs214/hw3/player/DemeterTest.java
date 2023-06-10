package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.board.Space;
import edu.cmu.cs214.hw3.board.Board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DemeterTest {
    private Player player;
    private Demeter worker;
    private Space space;
    private Space space1;
    private Space space2;
    private Board board;

    @Before
    public void setUp() {
        player = new Player(1);
        space = new Space(2, 2);
        space1 = new Space(2, 3);
        space2 = new Space(4, 4);
        worker = new Demeter(space, 1);
        board = new Board(5, 5);
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

    public void testBuiltDone1() {
        assertTrue(worker.buildBuilding(space2));
        assertTrue(worker.buildBuilding(space2));
        assertFalse(worker.builtDone());
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
    }

    public void testMoveWorkerLocation1() {
        assertFalse(worker.moveWorkerLocation(space2, board));
    }

    public void testSetWin() {
        worker.setWin();
        assertTrue(worker.getWin());
    }

    public void testBuildBuildling() {
        assertTrue(worker.buildBuilding(space1));
    }

    public void testBuildBuilding() {
        assertTrue(worker.buildBuilding(space1));
        assertFalse(worker.buildBuilding(space1));
    }
}