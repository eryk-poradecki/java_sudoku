package it.comprog.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        setFieldValue(value);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value >= 0 && value < 10) {
            this.value = value;
        }
    }

    public SudokuField clone() {
        return new SudokuField(getFieldValue());
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

        SudokuField valueCheck = (SudokuField) o;
        return new EqualsBuilder()
                .append(this.value, valueCheck.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 31)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(value)
                .toString();
    }

    @Override
    public int compareTo(SudokuField sudokuField) {
        if (sudokuField == null) {
            throw new NullPointerException("compared object is null");
        }
        return Integer.compare(getFieldValue(), sudokuField.getFieldValue());
    }

}
