package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.board.Board;
import edu.cmu.cs214.hw3.board.Space;

public class Minotaur implements Worker {
    private int x;
    private int y;
    private int player;
    private boolean win;

    public Minotaur(Space s, int p) {
        this.x = s.getRow();
        this.y = s.getCol();
        this.player = p;
        this.win = false;
    }

    /**
     * Set the x coordinate of the worker location.
     * 
     * @param x
     */
    public void setWorkerX(int x) {
        this.x = x;
    }

    /**
     * Set the y coordinate of the worker location.
     * 
     * @param y
     */
    public void setWorkerY(int y) {
        this.y = y;
    }

    /**
     * Retrieve the x coordinate of the worker location.
     *
     * @return the x coordinate of the worker location.
     */
    public int getWorkerX() {
        return this.x;
    }

    /**
     * Retrieve the y coordinate of the worker location.
     *
     * @return the y coordinate of the worker location.
     */
    public int getWorkerY() {
        return this.y;
    }

    /**
     * Retrieve the player of the worker.
     * 
     * @return the playerID who owns the worker.
     */
    public int getPlayer() {
        return player;
    }

    /**
     * Retrieve the winning status of the worker.
     *
     * @return {@code true} if the worker wins, or false otherwise.
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Set the winning status of the worker.
     */
    public void setWin() {
        this.win = true;
    }

    /**
     * Retrieve the building status of the worker.
     *
     * @return {@code true} if the worker is done building, or false otherwise.
     */
    public boolean builtDone() {
        return true;
    }

    /**
     * Check if the space is right next to the worker.
     * 
     * @param s the destination space that the worker should be neighboring with.
     * @return {@code true} if s is a neighboring space, or false otherwise.
     */
    public boolean isNeighbor(Space s) {
        int r = s.getRow();
        int c = s.getCol();
        int oldR = this.x;
        int oldC = this.y;
        if (r == oldR && c == oldC)
            return false;
        return (-1 <= (r - oldR)) && ((r - oldR) <= 1) && (-1 <= (c - oldC))
                && ((c - oldC) <= 1);
    }

    /**
     * Move the worker.
     * 
     * @param s     the destination space that the worker should move to.
     * @param board the game board.
     * @return {@code true} if the worker is successfully moved to a valid location
     *         false otherwise.
     */
    public boolean moveWorkerLocation(Space s, Board board) {
        if (!isNeighbor(s))
            return false;

        Space oldSpace = board.getSpace(this.x, this.y);
        int curLevel = oldSpace.getLevels();
        int targetLevel = s.getLevels();
        if (s.hasDome())
            return false;

        if (!s.isOccupied()) {
            if (!(targetLevel <= curLevel || targetLevel == curLevel + 1))
                return false;
            this.x = s.getRow();
            this.y = s.getCol();
            oldSpace.changeWorker(null);
            oldSpace.changeOccupied(oldSpace.hasDome());
            s.changeWorker(this);
            s.changeOccupied(true);

            if (s.getLevels() == 3) {
                this.win = true;
            }
            return true;
        }

        Worker occupiedWorker = s.getWorker();
        // if occupied by current player
        if (occupiedWorker.getPlayer() == this.player)
            return false;

        // if occupied by an opponent worker
        int dRow = s.getRow() - oldSpace.getRow();
        int dCol = s.getCol() - oldSpace.getCol();
        int newRow = s.getRow() + dRow;
        int newCol = s.getCol() + dCol;
        if (!board.isValidSpace(newRow, newCol))
            return false;
        Space opponentNewSpace = board.getSpace(newRow, newCol);
        if (opponentNewSpace.isOccupied())
            return false;

        opponentNewSpace.changeWorker(occupiedWorker);
        occupiedWorker.setWorkerX(newRow);
        occupiedWorker.setWorkerY(newCol);
        occupiedWorker.moveWorkerLocation(opponentNewSpace, board);
        opponentNewSpace.changeOccupied(true);
        s.changeOccupied(false);
        s.changeWorker(this);
        s.changeOccupied(true);
        this.x = s.getRow();
        this.y = s.getCol();
        if (s.getLevels() == 3)
            this.win = true;

        oldSpace.changeWorker(null);
        oldSpace.changeOccupied(oldSpace.hasDome());
        return true;
    }

    /**
     * Build a building on a space.
     * 
     * @param s the destination space that the worker should build a building on.
     * @return {@code true} if the building is successfully built on a valid
     *         location
     *         false otherwise.
     */
    public boolean buildBuilding(Space s) {
        return (isNeighbor(s) && s.changeBuilding());
    }

    /**
     * Retrieve the skip building status of the worker.
     *
     * @return {@code true} if the building stage needs to be ended.
     */
    public boolean endBuild() {
        return false;
    }
}
