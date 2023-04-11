package it.comprog;

import java.util.List;

public class SudokuColumn extends SudokuSubGrid implements SudokuSubscriber {

    SudokuColumn(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid, int value) {
        if (subGrid == col) {
            set(row, value);
            verify();
        }
    }
}
