package edu.cmu.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs214.hw3.board.Board;
import edu.cmu.cs214.hw3.board.Space;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;

/**
 * A game has a board and two players take turns to take action until one of
 * them wins.
 */
public class Game {
    private boolean gameOver;
    private Board gameBoard;
    private Player player1;
    private Player player2;
    private Player turn;
    private Player winner;
    private List<Game> history;
    private int stage;
    private Worker selectedWorker;
    private String lastChosen;
    private boolean chosen;

    private static final int NUMBER_OF_ROWS = 5;
    private static final int NUMBER_OF_COLS = 5;

    /**
     * Initialize a game.
     */

    public Game() {
        this.gameOver = false;
        this.gameBoard = new Board(NUMBER_OF_ROWS, NUMBER_OF_COLS);
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        this.turn = this.player1;
        this.winner = null;
        this.history = new ArrayList<>();
        this.stage = 0;
        this.selectedWorker = null;
        this.lastChosen = null;
        this.chosen = false;
    }

    public Game(Game other, List<Game> history, int stage) {
        this.gameOver = other.gameOver;
        this.gameBoard = other.gameBoard;
        this.turn = other.turn;
        this.winner = other.winner;
        this.history = history;
        this.stage = stage;
        this.player1 = other.player1;
        this.player2 = other.player2;
        this.selectedWorker = other.selectedWorker;
        this.lastChosen = other.lastChosen;
        this.chosen = other.chosen;
    }

    public Game(Game other, List<Game> history, int stage, Worker worker) {
        this.gameOver = other.gameOver;
        this.gameBoard = other.gameBoard;
        this.turn = other.turn;
        this.winner = other.winner;
        this.history = history;
        this.stage = stage;
        this.player1 = other.player1;
        this.player2 = other.player2;
        this.selectedWorker = worker;
        this.lastChosen = other.lastChosen;
        this.chosen = other.chosen;
    }

    public Game(Game other) {
        this.gameOver = other.gameOver;
        this.gameBoard = other.gameBoard;
        this.turn = other.turn;
        this.winner = other.winner;
        this.history = other.history;
        this.stage = other.stage;
        this.player1 = other.player1;
        this.player2 = other.player2;
        this.selectedWorker = other.selectedWorker;
        this.lastChosen = other.lastChosen;
        this.chosen = other.chosen;
    }

    /**
     * Retrieve the board.
     * 
     * @return the game board.
     */

    public Board getBoard() {
        return this.gameBoard;
    }

    /**
     * Retrieve player1.
     * 
     * @return the first player.
     */

    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Retrieve player2.
     * 
     * @return the second player.
     */

    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Retrieve the current player for this turn.
     * 
     * @return the player for the current turn.
     */

    public Player getCurrentPlayer() {
        return this.turn;
    }

    /**
     * Retrieve the selected worker.
     * 
     * @return the selected worker, or null if there is none.
     */

    public Worker getSelectedWorker() {
        return this.selectedWorker;
    }

    /**
     * After 1 round, change turn to the other player.
     */

    public void newTurn() {
        if (turn.equals(player1))
            turn = player2;
        else {
            turn = player1;
        }
    }

    /**
     * Check if the game is over.
     *
     * @return {@code true} if the game is over or false otherwise.
     */

    public boolean checkGameOver() {
        return this.gameOver;
    }

    /**
     * Retrieve the winner.
     * 
     * @return the player who is the winner, null if there is no winner.
     */

    public Player getWinner() {
        return this.winner;
    }

    /**
     * Retrieve the state of game (0 for placing worker, 1 for selecting worker, 2
     * for moving worker, 3 for building).
     * 
     * @return stage number of the game.
     */

    public int getStage() {
        return this.stage;
    }

    /**
     * Retrieve the most recently chosen worker type.
     * 
     * @return the worker type chosen.
     */
    public String getLastChosen() {
        return this.lastChosen;
    }

    /**
     * Retrieve whether the current player has already chosen a worker type.
     * 
     * @return true if the current player has chosen a worker type, or false
     *         otherwise.
     */
    public boolean getChosen() {
        return this.turn.getChosen();
    }

    /**
     * Perform action for a player.
     * 
     * @param x the row for the space selected.
     * @param y the column for space selected
     * @return the new game after the play.
     */
    public Game play(int x, int y) {
        if (this.gameOver)
            return this;
        if (stage == 0)
            return placeWorker(x, y);
        if (stage == 1)
            return select(x, y);
        if (stage == 2)
            return move(x, y);
        if (stage == 3)
            return build(x, y);
        return this;
    }

    /**
     * Set the worker type for the current player.
     * 
     * @param workerType
     * @return the new game after the setting the worker type for the current
     *         player.
     */
    public Game setWorkerType(String workerType) {
        Player player = getCurrentPlayer();
        player.setWorkerType(workerType);
        this.lastChosen = workerType;
        return this;
    }

    /**
     * Place the workers on the coordinates for the current turn player.
     * 
     * @param x the row for the worker.
     * @param y the column for the worker.
     * @return the new game after placing the worker.
     */

    public Game placeWorker(int x, int y) {
        if (!gameBoard.isValidSpace(x, y))
            return this;
        Space s1 = gameBoard.getSpace(x, y);
        if (!turn.placeWorker(s1))
            return this;

        List<Game> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);

        if (turn.getHasTwoWorkers()) {
            if (turn.equals(player1)) {// player2 still needs to place workers
                newTurn();
                return new Game(this, newHistory, this.stage);
            } else {
                newTurn();
                return new Game(this, newHistory, 1);
            }
        } else {// this player needs to place one more worker
            return new Game(this, newHistory, this.stage);
        }
    }

    /**
     * Select the workers at the specified location.
     * 
     * @param row the row for the worker.
     * @param col the column for the worker.
     * @return the new game after selecting the worker.
     */

    public Game select(int row, int col) {
        if (getWinner() != null)
            return this;
        if (!this.gameBoard.isValidSpace(row, col))
            return this;
        Space space = gameBoard.getSpace(row, col);
        Worker worker = space.getWorker();
        if (worker == null)
            return this;
        if (worker.getPlayer() != turn.getID())
            return this;
        List<Game> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);
        return new Game(this, newHistory, 2, worker);
    }

    /**
     * Move the selected worker to the specified location.
     * 
     * @param row the row of the target location.
     * @param col the column of the target location.
     * @return the new game after moving the worker.
     */

    public Game move(int row, int col) {
        if (getWinner() != null)
            return this;
        if (!this.gameBoard.isValidSpace(row, col))
            return this;
        if (this.selectedWorker == null)
            return new Game(this, this.history, 1, null);
        // Game currGame = new Game(this);
        Space newSpace = gameBoard.getSpace(row, col);
        boolean success = turn.moveWorker(newSpace, this.selectedWorker, this.gameBoard);
        if (success) {
            List<Game> newHistory = new ArrayList<>(this.history);
            // newHistory.add(currGame);
            if (turn.getStatus()) {
                this.winner = turn;
                this.gameOver = true;
            }

            return new Game(this, newHistory, 3);
        }
        return this;
    }

    /**
     * Build another level at the specified location.
     * 
     * @param row the row of the target location.
     * @param col the column of the target location.
     * @return the new game after building.
     */
    public Game build(int row, int col) {
        if (getWinner() != null)
            return this;
        if (!gameBoard.isValidSpace(row, col))
            return this;
        Space buildSpace = gameBoard.getSpace(row, col);
        Game currGame = new Game(this);
        boolean success = turn.buildBuidling(buildSpace, this.selectedWorker);
        if (!success)
            return this;

        List<Game> newHistory = new ArrayList<>(this.history);
        newHistory.add(currGame);
        if (selectedWorker.builtDone()) {
            newTurn();
            return new Game(this, newHistory, 1, null);
        }
        return new Game(this, newHistory, 3);

    }

    /**
     * End the currnet building stage.
     * 
     * @return the new game after ending the building stage and start a next round.
     */
    public Game endBuild() {
        List<Game> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);
        if (!selectedWorker.endBuild())
            return this;
        newTurn();
        return new Game(this, newHistory, 1);
    }
}
