package edu.cmu.cs214.hw3.board;

/**
 * The board, made up of 5*5 spaces, represents game board where the game takes
 * place and the players take turns to move a worker or build a building on.
 */

public class Board {
    private Space[][] playBoard;
    private int rows;
    private int cols;

    /**
     * Initialize the game board with spaces spanning 5 rows and 5 columns.
     * 
     * @param rows the total number of rows.
     * @param cols the total number of cols.
     */
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.playBoard = new Space[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.playBoard[i][j] = new Space(i, j);
            }
        }
    }

    /**
     * Checks whether the coordinates is a valid location on the board.
     * 
     * @param row the row index.
     * @param col the column index.
     * @return {@code true} if the space is on the board and false otherwise.
     */
    public boolean isValidSpace(int row, int col) {
        return (row >= 0 && row < rows) && (col >= 0 && col < cols);
    }

    /**
     * Retrieves the specified space.
     * 
     * @param r the row index.
     * @param c the column index.
     * @return {@link Space} where the coordinates is as specified.
     */
    public Space getSpace(int r, int c) throws ArrayIndexOutOfBoundsException {
        if (isValidSpace(r, c)) {
            return playBoard[r][c];
        }
        return null;
    }

}
