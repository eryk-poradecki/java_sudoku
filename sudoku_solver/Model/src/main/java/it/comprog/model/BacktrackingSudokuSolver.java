package it.comprog.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.comprog.model.SudokuUtils.gridSize;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public boolean backtrack(int col, int row, SudokuBoard sudokuBoard) {
        // checks if the end of the board was reached
        if (col == gridSize && row == gridSize - 1) {
            return true;
        }

        // checks if the end of the row was reached, switches to the next one if true
        if (col == gridSize) {
            row++;
            col = 0;
        }

        // checks if the current cell is filled
        if (sudokuBoard.get(col, row) != 0) {
            backtrack(col + 1, row, sudokuBoard);
        }

        for (int value = 1; value <= gridSize; value++) {
            if (sudokuBoard.canInsertValue(col, row, value)) {
                sudokuBoard.set(col, row, value);
                if (backtrack(col + 1, row, sudokuBoard)) {
                    return true;
                }

                sudokuBoard.set(col, row, 0);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
