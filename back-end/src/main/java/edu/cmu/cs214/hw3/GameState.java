package edu.cmu.cs214.hw3;

import java.util.Arrays;

import edu.cmu.cs214.hw3.board.Board;
import edu.cmu.cs214.hw3.board.Space;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;

/**
 * A game state records and keeps track of the state of a game.
 */

public final class GameState {
    private final Cell[] cells;
    private final int winner;
    private final int turn;
    private final int stage;
    private final String lastChosen;
    private final boolean chosen;
    private static final int NUMBER_OF_ROWS = 5;
    private static final int NUMBER_OF_COLS = 5;

    /**
     * Initialize a game state.
     */
    private GameState(Cell[] cells,
            Player winner,
            Player turn,
            Player player1,
            Player player2,
            int stage,
            String lastChosen,
            boolean chosen) {
        this.cells = cells;
        if (winner != null && winner.equals(player1))
            this.winner = 1;
        else if (winner != null && winner.equals(player2))
            this.winner = 2;
        else
            this.winner = 0;
        if (turn.equals(player1))
            this.turn = 1;
        else
            this.turn = 2;
        this.stage = stage;
        this.lastChosen = lastChosen;
        this.chosen = chosen;
    }

    /**
     * Retrieve a game state from a game.
     * 
     * @param game the ongoing game
     * @return the game state of the game.
     */

    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        return new GameState(cells, game.getWinner(), game.getCurrentPlayer(), game.getPlayer1(), game.getPlayer2(),
                game.getStage(), game.getLastChosen(), game.getChosen());
    }

    /**
     * Retrieve the cells from a game state.
     * 
     * @return the array of cells of the game state.
     */

    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * Retrieve the stage of the game state.
     * 
     * @return the stage of the game state.
     */

    public int getStage() {
        return this.stage;
    }

    public String getLastChosen() {
        return this.lastChosen;
    }

    /**
     * toString() of GameState will return the string representing
     * the GameState in JSON format.
     */
    @Override
    public String toString() {
        return """
                {
                    "cells": %s,
                    "turn": %d,
                    "winner": %d,
                    "stage": %d,
                    "lastChosen": "%s",
                    "chosen": %b
                }
                """.formatted(Arrays.toString(this.cells), this.turn, this.winner, this.stage, this.lastChosen,
                this.chosen);

    }

    /**
     * Retrieve the cells from a game.
     * 
     * @param game the ongoing game
     * @return array of cells with their features stored.
     */

    private static Cell[] getCells(Game game) {
        Cell[] cells = new Cell[NUMBER_OF_ROWS * NUMBER_OF_COLS];
        Board board = game.getBoard();
        Worker selectedWorker = game.getSelectedWorker();
        int selectedR = -1;
        int selectedC = -1;
        if (selectedWorker != null) {
            selectedR = selectedWorker.getWorkerX();
            selectedC = selectedWorker.getWorkerY();
        }
        for (int x = 0; x < NUMBER_OF_ROWS; x++) {
            for (int y = 0; y < NUMBER_OF_COLS; y++) {
                String text = "";
                boolean hasPlayer1Worker = false;
                boolean hasPlayer2Worker = false;
                boolean selected = (x == selectedR) && (y == selectedC);
                Space space = board.getSpace(x, y);
                int playerID = 0;
                if (space.getWorker() != null)
                    playerID = space.getWorker().getPlayer();
                if (playerID == 1) {
                    text = "1: " + game.getPlayer1().getWorkerType();
                    hasPlayer1Worker = true;
                    for (int i = 0; i < space.getLevels(); i++) {
                        text = "[" + text + "]";
                    }
                } else if (playerID == 2) {
                    text = "2: " + game.getPlayer2().getWorkerType();
                    playerID = 2;
                    hasPlayer2Worker = true;
                    for (int i = 0; i < space.getLevels(); i++) {
                        text = "[" + text + "]";
                    }
                } else if (playerID == 0) {
                    if (space.hasDome()) {
                        text = "[[[O]]]";
                    } else {
                        text = " ";
                        for (int i = 0; i < space.getLevels(); i++) {
                            text = "[" + text + "]";
                        }
                    }
                }
                cells[NUMBER_OF_COLS * y + x] = new Cell(x, y, text, hasPlayer1Worker, hasPlayer2Worker, selected,
                        playerID);
            }
        }
        return cells;
    }
}

/**
 * A cell is one of the cells on the board with specific features stored.
 */
class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean hasPlayer1Worker;
    private final boolean hasPlayer2Worker;
    private final boolean selected;
    private final int playerId;

    /**
     * Initialize a cell.
     */

    Cell(int x, int y, String text, boolean hasPlayer1Worker, boolean hasPlayer2Worker, boolean selected,
            int playerId) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.hasPlayer1Worker = hasPlayer1Worker;
        this.hasPlayer2Worker = hasPlayer2Worker;
        this.selected = selected;
        this.playerId = 0;
    }

    /**
     * Retrieve the x coordinate of the cell.
     * 
     * @return the x coordinate of the cell.
     */

    public int getX() {
        return x;
    }

    /**
     * Retrieve the y coordinate of the cell.
     * 
     * @return the y coordinate of the cell.
     */

    public int getY() {
        return y;
    }

    /**
     * Retrieve the text coordinate of the cell.
     * 
     * @return the text coordinate of the cell.
     */

    public String getText() {
        return this.text;
    }

    /**
     * Retrieve the hasPlayer1Worker feature of the cell.
     * 
     * @return {true} if the cell has a worker that belongs to player1 or false
     *         otherwise.
     */

    public boolean getHasPlayer1Worker() {
        return this.hasPlayer1Worker;
    }

    /**
     * Retrieve the hasPlayer2Worker feature of the cell.
     * 
     * @return {true} if the cell has a worker that belongs to player2 or false
     *         otherwise.
     */
    public boolean getHasPlayer2Worker() {
        return this.hasPlayer2Worker;
    }

    /**
     * Retrieve the selected feature of the cell.
     * 
     * @return {true} if the cell has been selected by the player for the current
     *         round or false otherwise.
     */
    public boolean getSeleted() {
        return this.selected;
    }

    /**
     * Retrieve the playerId feature of the cell.
     * 
     * @return the playerId of the player if their worker is at the cell; or 0
     *         otherwise.
     * 
     */
    public int getPlayerId() {
        return this.playerId;
    }

    @Override
    public String toString() {
        return """
                {
                    "text": "%s",
                    "hasPlayer1Worker": %b,
                    "hasPlayer2Worker": %b,
                    "selected": %b,
                    "x": %d,
                    "y": %d,
                    "playerId": %d
                }
                """.formatted(this.text, this.hasPlayer1Worker, this.hasPlayer2Worker, this.selected, this.x, this.y,
                this.playerId);
    }
}