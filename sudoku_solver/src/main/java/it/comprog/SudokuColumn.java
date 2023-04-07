package it.comprog;

public class SudokuColumn extends SudokuSubGrid implements SudokuSubscriber {

    SudokuColumn(SudokuField[] sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public void update(int col, int row, int subGrid) {
        if (subGrid == col) {
            verify();
        }
    }
}
