package it.comprog;

import static it.comprog.SudokuUtils.gridSize;

public class SudokuSubGrid {

    private SudokuField[] sudokuFields = new SudokuField[gridSize];

    private boolean valid;

    public SudokuSubGrid(SudokuField[] sudokuFields) {
        for (int i = 0; i < gridSize; i++) {
            this.sudokuFields[i] = sudokuFields[i];
        }
        verify();
    }

    public SudokuField get (int n) {
        return sudokuFields[n].clone();
    }

    public boolean isValid() {
        return valid;
    }

    public boolean verify() {
        for (int i = 0; i < gridSize; i++) {
            if (sudokuFields[i].getFieldValue() != 0) {
                for (int j = i + 1; j < gridSize; j++) {
                    if (sudokuFields[i].getFieldValue() == sudokuFields[j]. getFieldValue()) {
                        valid = false;
                        return false;
                    }
                }
            }
        }
        valid = true;
        return true;
    }
}
