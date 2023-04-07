package it.comprog;

public class SudokuRow extends SudokuSubGrid implements SudokuSubscriber {

    SudokuRow(SudokuField[] sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid) {
        if (subGrid == row) {
            verify();
        }
    }
}
