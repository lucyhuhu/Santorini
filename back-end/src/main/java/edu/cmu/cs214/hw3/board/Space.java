package edu.cmu.cs214.hw3.board;

import edu.cmu.cs214.hw3.player.Worker;

/**
 * A space represents one square on the board, which can be identified by its
 * coordiantes and may or may not have a worker or building.
 */

public class Space {
    private int row;
    private int col;
    private boolean occupied;
    private Worker worker;
    private int levels;

    /**
     * Initialize a space with specified row and column.
     * 
     * @param r the row index of the space.
     * @param c the column index of the space.
     */
    public Space(int r, int c) {
        this.row = r;
        this.col = c;
        this.occupied = false;
        this.worker = null;
        this.levels = 0;
    }

    /**
     * Retrives the row of the specified space.
     *
     * @return the row index of the space.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrives the column of the specified space.
     *
     * @return the column index of the space.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks whether the space is occupied (either has a worker or a dome
     * already).
     * 
     * @return {@code true} if the space is already occupied and false otherwise.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Change occupied status.
     * 
     * @param changeTo the state that occupied should be changed to.
     */
    public void changeOccupied(boolean changeTo) {
        this.occupied = changeTo;
    }

    /**
     * Retrives the worker at the location; null if no worker present.
     *
     * @return {@link Worker} on the space.
     */
    public Worker getWorker() {
        return worker;
    }

    public int getLevels() {
        return this.levels;
    }

    public boolean hasDome() {
        return (this.levels > 3);
    }

    /**
     * Change the status of worker on the space (either a worker is currently placed
     * or removed).
     *
     * @param w The worker to be placed at the space; null if want to remove worker.
     * @return {@code true} if the worker is successfully changed and false
     *         otherwise.
     */
    public boolean changeWorker(Worker w) {
        if (w == null) {
            this.worker = null;
            this.occupied = hasDome();
            return true;
        } else {
            if (occupied)
                return false;
            this.worker = w;
            this.occupied = true;
            return true;
        }
    }

    /**
     * Change the status of building on the space (either start a building or add a
     * layer).
     * 
     * @return {@code true} if the building is successfully added a level and false
     *         otherwise
     */
    public boolean changeBuilding() {
        if (occupied)
            return false;
        this.levels++;
        if (hasDome())
            this.occupied = true;
        return true;
    }

}
