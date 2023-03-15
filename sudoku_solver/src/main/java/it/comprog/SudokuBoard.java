package it.comprog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private static final int gridSize = 9;
    private static final int boxSize = 3;

    private int[][] board = new int[gridSize][gridSize];

    // constructor for randomized boards
    SudokuBoard() {

    }

    // constructor for initializing known boards
    SudokuBoard(int[][] board) {
        this.board = board;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, int value) {
        board[row][col] = value;
    }

    public int[] getRow(int row) {
        return board[row].clone();
    }

    public int[] getColumn(int col) {
       int[] column = new int [gridSize];
       for (int i = 0; i < gridSize; i++) {
           column[i] = get(i, col);
       }
       return column;
    }

    public int[][] getBox(int row, int col) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        int[][] box = new int [boxSize][boxSize];
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                box[i][j] = get(i + boxRow, j + boxCol);
            }
        }
        return box;
    }

    // checks if a given value can be inserted in a cell
    public boolean canInsertValue(int row, int col, int value) {
        // checks for a duplicate in the same row and column
        for (int i = 0; i < gridSize; i++) {
            if (get(row, i) == value || get(i, col) == value) {
                return false;
            }
        }

        // position of the first cell in the box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        // checks for duplicates in the box
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (get(boxRow + i, boxCol + j) == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean backtrack(int row, int col) {
        // checks if the end of the board was reached
        if (row == gridSize - 1 && col == gridSize) {
            return true;
        }

        // checks if the end of the row was reached, switches to the next one if true
        if (col == gridSize) {
            row++;
            col = 0;
        }

        // checks if the current cell is filled
        if (get(row, col) != 0) {
            backtrack(row, col + 1);
        }

        for (int value = 1; value < gridSize + 1; value++) {
            if (canInsertValue(row, col, value)) {
                set(row, col, value);
                if (backtrack(row, col + 1)) {
                    return true;
                }

                set(row, col, 0);
            }
        }
        return false;
    }

    void fillBoard() {
        List<Integer> firstRow = new ArrayList<>();
        for (int i = 1; i < gridSize + 1; i++) {
            firstRow.add(i);
        }
        Collections.shuffle(firstRow);
        for (int i = 0; i < gridSize; i++) {
            set(0, i, firstRow.get(i));
        }

        backtrack(1,0);
    }

}
