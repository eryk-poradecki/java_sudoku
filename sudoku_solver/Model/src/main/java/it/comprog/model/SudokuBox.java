package it.comprog.model;

import java.util.List;

import static it.comprog.model.SudokuUtils.boxSize;

public class SudokuBox extends SudokuSubGrid implements SudokuSubscriber {

    SudokuBox(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid, int value) {
        int boxCol = col - col % boxSize;
        int boxRow = row - row % boxSize;
        if (subGrid == boxRow * 3 + boxCol) {
            int i = row % boxSize * 3 + col % boxSize;

            set(i, value);
            verify();
        }
    }

    @Override
    public SudokuBox clone() {
        return new SudokuBox(getSudokuFields());
    }
}
