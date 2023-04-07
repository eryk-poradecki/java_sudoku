package it.comprog;

import static it.comprog.SudokuUtils.boxSize;

public class SudokuBox extends SudokuSubGrid implements SudokuSubscriber {

    SudokuBox(SudokuField[] sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid) {
        col = col - col % boxSize;
        row = row - row % boxSize;
        if (subGrid == (row * 3 + col)) {
            verify();
        }
    }
}
