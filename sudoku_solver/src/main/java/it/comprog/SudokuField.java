package it.comprog;

public class SudokuField implements Cloneable {

    private int value;

    SudokuField() {

    }

    SudokuField(int value) {
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

    @Override
    public SudokuField clone() {
        SudokuField clonedField = new SudokuField();
        clonedField.setFieldValue(getFieldValue());
        return clonedField;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        SudokuField valueCheck = (SudokuField) o;
        return getFieldValue() == valueCheck.value;
    }
}
