package it.comprog.model;

import java.util.List;

public class SudokuRow extends SudokuSubGrid implements SudokuSubscriber {

    SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid, int value) {
        if (subGrid == row) {
            set(col, value);
            verify();
        }
    }

    @Override
    public SudokuRow clone() {
        return new SudokuRow(getSudokuFields());
    }
}
