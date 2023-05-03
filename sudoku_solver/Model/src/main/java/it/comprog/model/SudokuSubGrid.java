package it.comprog.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.comprog.model.SudokuUtils.gridSize;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sudokuFields", sudokuFields)
                .append("valid", valid)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuFields)
                .toHashCode();
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

        SudokuSubGrid subGrid = (SudokuSubGrid) o;
        return new EqualsBuilder()
                .append(this.sudokuFields, subGrid.sudokuFields)
                .isEquals();
    }
}
