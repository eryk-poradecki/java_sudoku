package it.comprog;

import java.util.*;
import static it.comprog.SudokuUtils.gridSize;
import static it.comprog.SudokuUtils.boxSize;

public class SudokuBoard {

    private final SudokuField[][] board = new SudokuField[gridSize][gridSize];

    private final SudokuSolver sudokuSolver;

    private List<Map.Entry<SudokuSubscriber, Integer>> sudokuSubscribers = new ArrayList<>();

    SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j ++) {
                board[i][j] = new SudokuField();
            }
        }
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int col, int row) {
        return board[col][row].getFieldValue();
    }

    public void set(int col, int row, int value) {
        board[col][row].setFieldValue(value);
        notifySubscribers(col, row);
    }

    public SudokuRow getRow(int row) {
        SudokuField[] sudokuFields = new SudokuField[gridSize];
        for (int i = 0; i < gridSize; i ++) {
            sudokuFields[i] = this.board[i][row];
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        subscribe(sudokuRow, row);
        return sudokuRow;
    }

    public SudokuColumn getColumn(int col) {
        SudokuField[] sudokuFields = new SudokuField[gridSize];
        for (int i = 0; i < gridSize; i ++){
            sudokuFields[i] = this.board[col][i];
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        subscribe(sudokuColumn, col);
        return sudokuColumn;
    }

    public SudokuBox getBox(int col, int row) {
        SudokuField[] sudokuFields = new SudokuField[gridSize];
        int boxCol = col - col % boxSize;
        int boxRow = row - row % boxSize;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                sudokuFields[i * boxSize + j] = this.board[i + boxCol][j + boxRow];
            }
        }
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);
        subscribe(sudokuBox, boxRow * 3 + boxCol);
        return sudokuBox;
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

    public boolean checkBoardValidity() {
        // checks if all rows and columns are valid
        for (int i = 0; i < gridSize; i++) {
            if (!getRow(i).isValid()) {
                return false;
            }
            if (!getColumn(i).isValid()) {
                return false;
            }
        }

        // checks if all boxes are valid
        for (int i = 0; i < gridSize; i += 3) {
            for (int j = 0; j < gridSize; j += 3) {
                if (!getBox(i,j).isValid()) {
                    return false;
                }
            }
        }

        return true;
    }

    private void subscribe(SudokuSubscriber subscriber, int subGrid) {
        Map.Entry<SudokuSubscriber, Integer> sub = new AbstractMap.SimpleEntry<>(subscriber, subGrid);
        sudokuSubscribers.add(sub);
    }

    private void unsubscribe(SudokuSubGrid subGrid){
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            if (sub.getKey() == subGrid) {
                sudokuSubscribers.remove(sub);
                return;
            }
        }
    }

    private void notifySubscribers(int x, int y) {
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            sub.getKey().update(x, y, sub.getValue());
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }


}
