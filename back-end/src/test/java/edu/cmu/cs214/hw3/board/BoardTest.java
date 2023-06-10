package edu.cmu.cs214.hw3.board;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board(5, 5);
    }

    @Test
    public void testIsValidSpace1() {
        assertTrue(board.isValidSpace(0, 0));
    }

    public void testIsValidSpace2() {
        assertTrue(board.isValidSpace(0, 1));
    }

    public void testIsValidSpace3() {
        assertTrue(board.isValidSpace(4, 4));
    }

    public void testIsValidSpace4() {
        assertFalse(board.isValidSpace(4, 5));
    }

    public void testgetSpace1() {
        Space space03 = board.getSpace(0, 3);
        assertEquals(0, space03.getRow());
        assertEquals(3, space03.getCol());
    }

    public void testgetSpace2() {
        Space space44 = board.getSpace(4, 4);
        assertEquals(4, space44.getRow());
        assertEquals(4, space44.getCol());
    }
}
