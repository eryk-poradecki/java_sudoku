package it.comprog;

import java.util.*;

import static it.comprog.SudokuUtils.boxSize;
import static it.comprog.SudokuUtils.gridSize;


public class SudokuBoard {

    private final SudokuField[][] board = new SudokuField[gridSize][gridSize];

    private final SudokuSolver sudokuSolver;

    private List<Map.Entry<SudokuSubscriber, Integer>> sudokuSubscribers = new ArrayList<>();

    SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = new SudokuField();
            }
        }
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int col, int row) {
        return board[col][row].getFieldValue();
    }

    public void set(int col, int row, int value) {
        if (get(col, row) != value) {
            board[col][row].setFieldValue(value);
            notifySubscribers(col, row, value);
        }
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, board[i][row].clone());
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        subscribe(sudokuRow, row);
        return sudokuRow;
    }

    public SudokuColumn getColumn(int col) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, board[col][i].clone());
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        subscribe(sudokuColumn, col);
        return sudokuColumn;
    }

    public SudokuBox getBox(int col, int row) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        int boxCol = col - col % boxSize;
        int boxRow = row - row % boxSize;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                sudokuFields.set(i * boxSize + j, board[boxCol + i][boxRow + j].clone());
            }
        }
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);
        subscribe(sudokuBox, boxRow * 3 + boxCol);
        return sudokuBox;
    }

    // checks if a given value can be inserted in a cell
    public boolean canInsertValue(int col, int row, int value) {
        return !getRow(col).contains(value) && !getColumn(row).contains(value) && !getBox(row, col).contains(value);
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

    public void unsubscribe(SudokuSubGrid subGrid) {
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            if (sub.getKey() == subGrid) {
                sudokuSubscribers.remove(sub);
                break;
            }
        }
    }

    private void notifySubscribers(int col, int row, int value) {
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            sub.getKey().update(col, row, sub.getValue(), value);
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }


}
