package edu.cmu.cs214.hw3.player;

import java.util.ArrayList;

import edu.cmu.cs214.hw3.board.Board;
import edu.cmu.cs214.hw3.board.Space;

/**
 * A player is one of the two players in a game, each of which owns two workers.
 */

public class Player {
    private ArrayList<Worker> workers;
    private boolean win;
    private boolean hasTwoWorkers;
    private String workerType;
    private boolean chosen;
    private int id;

    private static final String NORMAL = "Normal";
    private static final String DEMETER = "Demeter";
    private static final String MINOTAUR = "Minotaur";
    private static final String PAN = "Pan";

    /**
     * Initialize a player.
     * 
     * @param id the player id.
     */

    public Player(int id) {
        this.workers = new ArrayList<>();
        this.win = false;
        this.hasTwoWorkers = false;
        this.workerType = NORMAL;
        this.chosen = false;
        this.id = id;

    }

    /**
     * Set the worker type for the player.
     * 
     * @param workerType
     */
    public void setWorkerType(String workerType) {
        if (workerType != null) {
            this.workerType = workerType;
            this.chosen = true;
        }
    }

    /**
     * Get the workers of the player.
     * 
     * @return the arraylist of worker.
     */
    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    /**
     * Get the winning status of the player.
     * 
     * @return true if the player wins and false otherwise.
     */
    public boolean getStatus() {
        return win;
    }

    /**
     * Set the winning status for the player.
     */
    public void setWin() {
        this.win = true;
    }

    /**
     * Check if 2 workers are placed.
     * 
     * @return {true} if the player already has two workers, or false otherwise.
     */
    public boolean getHasTwoWorkers() {
        return hasTwoWorkers;
    }

    /**
     * Get the worker type of the player.
     * 
     * @return the worker type of the player.
     */
    public String getWorkerType() {
        return workerType;
    }

    /**
     * Check if the player has chosen a worker type.
     * 
     * @return true if the player has chosen a worker type, or false
     *         otherwise.
     */
    public boolean getChosen() {
        return this.chosen;
    }

    /**
     * Retrieve the player id.
     * 
     * @return the player id.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Place a worker on a given space.
     * 
     * @param s the destination space to place the worker.
     * @return {@code true} if the worker is successfully placed, false otherwise.
     */

    public boolean placeWorker(Space s) {
        if (workers.size() >= 2)
            return false;
        if (workers.size() == 1) {
            int r = getWorkers().get(0).getWorkerX();
            int c = getWorkers().get(0).getWorkerY();
            if (r == s.getRow() && c == s.getCol())
                return false;
        }
        Worker w = null;
        switch (workerType) {
            case NORMAL:
                w = new NormalWorker(s, this.id);
                break;
            case DEMETER:
                w = new Demeter(s, this.id);
                break;
            case MINOTAUR:
                w = new Minotaur(s, this.id);
                break;
            case PAN:
                w = new Pan(s, this.id);
                break;
            default:
                break;
        }
        this.workers.add(w);
        s.changeWorker(w);
        if (this.workers.size() == 2)
            this.hasTwoWorkers = true;
        return true;

    }

    /**
     * Move a worker to a space and update player winning status.
     * 
     * @param s      the destination location
     * @param worker the worker that should be moved, either 0 or 1
     * @param board  the game board
     * @return {@code true} if the worker is successfully moved, false otherwise.
     */
    public boolean moveWorker(Space s, Worker worker, Board board) {
        if (!worker.moveWorkerLocation(s, board))
            return false;
        this.win = this.win || worker.getWin();
        return true;
    }

    /**
     * Build a level at a space.
     * 
     * @param s      the destination location
     * @param worker the worker next to the location, either 0 or 1
     * @return {@code true} if the level is successfully built, false otherwise.
     */
    public boolean buildBuidling(Space s, Worker worker) {
        return worker.buildBuilding(s);
    }

}
