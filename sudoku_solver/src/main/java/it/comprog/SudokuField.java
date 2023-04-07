package it.comprog;

public class SudokuField {

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

    public SudokuField clone() {
        SudokuField clonedField = new SudokuField();
        clonedField.setFieldValue(getFieldValue());
        return clonedField;
    }
}
