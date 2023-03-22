package it.comprog;

import java.util.*;

public class SudokuBoard {

    private static final int gridSize = 9;
    private static final int boxSize = 3;

    private final int[][] board = new int[gridSize][gridSize];

    private final SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
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

    public int[] boxToArray(int[][] box) {
        int[] result = new int[9];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(box[i], 0, result, i * 3, 3);
        }
        return result;
    }

    public boolean checkArrayValidity(int[] array) {
        for (int i = 0; i < gridSize; i++) {
            if (array[i] <= 0 || array[i] > 9) {
                return false;
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i : array) {
            if (s.contains(i)) {
                return false;
            }
            s.add(i);
        }
        return true;
    }

    public boolean checkBoardValidity() {
        // checks if all rows and columns are valid
        for (int i = 0; i < gridSize; i++) {
            if (!checkArrayValidity(getRow(i))) {
                return false;
            }
            if (!checkArrayValidity(getColumn(i))) {
                return false;
            }
        }

        // checks if all boxes are valid
        for (int i = 0; i < gridSize; i += 3) {
            for (int j = 0; j < gridSize; j += 3) {
                if (!checkArrayValidity(boxToArray(getBox(i,j)))) {
                    return false;
                }
            }
        }

        return true;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }


}
