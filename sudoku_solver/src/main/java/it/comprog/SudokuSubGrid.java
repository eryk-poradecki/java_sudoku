package it.comprog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.comprog.SudokuUtils.gridSize;

public abstract class SudokuSubGrid {

    private List<SudokuField> sudokuFields;

    private boolean valid;

    public SudokuSubGrid(List<SudokuField> sudokuFields) {
        this.sudokuFields = new ArrayList<>(sudokuFields);
        verify();
    }

    public SudokuField get(int n) {
        return sudokuFields.get(n).clone();
    }

    protected void set(int n, int value) {
        sudokuFields.get(n).setFieldValue(value);
    }

    public boolean isValid() {
        return valid;
    }

    public boolean contains(int value) {
        return sudokuFields.contains(new SudokuField(value));
    }

    public boolean verify() {
        for (int i = 1; i < gridSize + 1; i++) {
            if (Collections.frequency(sudokuFields, new SudokuField(i)) > 1) {
                    valid = false;
                    return false;
                }
            }
        valid = true;
        return true;
    }
}
