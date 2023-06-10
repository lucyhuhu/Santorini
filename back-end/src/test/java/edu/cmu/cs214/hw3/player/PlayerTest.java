package edu.cmu.cs214.hw3.player;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.board.Space;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PlayerTest {
    private Player player1;
    private Space space1;
    private Space space2;
    private Space space3;

    @Before
    public void setUp() {
        space1 = new Space(0, 3);
        space2 = new Space(2, 1);
        space3 = new Space(4, 0);
        player1 = new Player(1);
    }

    @Test
    public void testPlaceWorker() {
        assertTrue(player1.placeWorker(space1));
    }

    public void testPlaceWorker1() {
        assertTrue(player1.placeWorker(space2));
    }

    public void testPlaceWorker2() {
        assertFalse(player1.placeWorker(space3));
    }

    public void testGetWorkers() {
        assertEquals(2, player1.getWorkers().size());
        assertEquals(space1.getRow(), player1.getWorkers().get(0).getWorkerX());
        assertEquals(space1.getCol(), player1.getWorkers().get(0).getWorkerY());
    }
}
