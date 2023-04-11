package it.comprog;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.comprog.SudokuUtils.gridSize;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public boolean backtrack(int row, int col, SudokuBoard sudokuBoard) {
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
        if (sudokuBoard.get(row, col) != 0) {
            backtrack(row, col + 1, sudokuBoard);
        }

        for (int value = 1; value < gridSize + 1; value++) {
            if (sudokuBoard.canInsertValue(row, col, value)) {
                sudokuBoard.set(row, col, value);
                if (backtrack(row, col + 1, sudokuBoard)) {
                    return true;
                }

                sudokuBoard.set(row, col, 0);
            }
        }
        return false;
    }

    private void setInitialRow(SudokuBoard sudokuBoard) {
        List<Integer> firstRow = Arrays.asList(new Integer[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            firstRow.set(i, i + 1);
        }
        Collections.shuffle(firstRow);
        for (int i = 0; i < gridSize; i++) {
            sudokuBoard.set(i, 0, firstRow.get(i));
        }
    }

    @Override
    public void solve(SudokuBoard sudokuBoard) {
        setInitialRow(sudokuBoard);
        backtrack(0,0, sudokuBoard);
    }
}
