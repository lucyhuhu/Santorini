package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.board.Board;
import edu.cmu.cs214.hw3.board.Space;

public interface Worker {

    /**
     * Retrieve the x coordinate of the worker location.
     *
     * @return the x coordinate of the worker location.
     */
    int getWorkerX();

    /**
     * Retrieve the y coordinate of the worker location.
     *
     * @return the y coordinate of the worker location.
     */
    int getWorkerY();

    /**
     * Set the x coordinate of the worker location.
     * 
     * @param x
     */
    void setWorkerX(int x);

    /**
     * Set the y coordinate of the worker location.
     * 
     * @param y
     */
    void setWorkerY(int y);

    /**
     * Retrieve the player of the worker.
     *
     * @return the player who owns the worker.
     */
    int getPlayer();

    /**
     * Retrieve the winning status of the worker.
     *
     * @return {@code true} if the worker wins, or false otherwise.
     */
    boolean getWin();

    /**
     * Set the winning status of the worker.
     */
    void setWin();

    /**
     * Retrieve the building status of the worker.
     *
     * @return {@code true} if the worker is done building, or false otherwise.
     */
    boolean builtDone();

    /**
     * Check if the space is right next to the worker.
     *
     * @param s the destination space that the worker should be neighboring with.
     * @return {@code true} if s is a neighboring space, or false otherwise.
     */
    boolean isNeighbor(Space s);

    /**
     * Move the worker.
     *
     * @param s     the destination space that the worker should move to.
     * @param board the game board.
     * @return {@code true} if the worker is successfully moved to a valid location
     *         false otherwise.
     */
    boolean moveWorkerLocation(Space s, Board board);

    /**
     * Build a building on a space.
     * 
     * @param s the destination space that the worker should build a building on.
     * @return {@code true} if the building is successfully built on a valid
     *         location
     *         false otherwise.
     */
    boolean buildBuilding(Space s);

    /**
     * Retrieve the skip building status of the worker.
     *
     * @return {@code true} if the building stage needs to be ended.
     */
    boolean endBuild();
}
