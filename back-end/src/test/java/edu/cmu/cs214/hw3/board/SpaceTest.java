package edu.cmu.cs214.hw3.board;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.NormalWorker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SpaceTest {
    private Space space00;
    private Space space23;
    private NormalWorker worker;
    private Player player;

    @Before
    public void setUp() {
        player = new Player(1);
        space00 = new Space(0, 0);
        space23 = new Space(2, 3);
        worker = new NormalWorker(space23, 1);
        space23.changeWorker(worker);
        space23.changeOccupied(true);
    }

    @Test
    public void testGetRow() {
        assertEquals(0, space00.getRow());
    }

    public void testGetCol() {
        assertEquals(3, space23.getCol());
    }

    public void testIsOccupied() {
        assertFalse(space00.isOccupied());
    }

    public void testChangeOccupied() {
        assertTrue(space23.isOccupied());
    }

    public void testGetWorker() {
        assertEquals(null, space00.getWorker());
    }

    public void testGetWorker1() {
        assertEquals(worker, space23.getWorker());
    }

    public void testChangeWorker() {
        space23.changeWorker(null);
        assertEquals(null, space23.getWorker());
    }

    public void testChangeBuilding() {
        space00.changeBuilding();
        assertEquals(1, space00.getLevels());
    }
}
