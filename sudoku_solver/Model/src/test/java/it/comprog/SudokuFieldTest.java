package it.comprog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void testSetGetValue_validValue() {
        SudokuField sudokuField = new SudokuField();

        assertEquals(0, sudokuField.getFieldValue());
        sudokuField.setFieldValue(9);
        assertEquals(9, sudokuField.getFieldValue());
    }

    @Test
    void testSetGetValue_invalidValue(){
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(-3);
        assertNotEquals(-3, sudokuField.getFieldValue());
        sudokuField.setFieldValue(12);
        assertNotEquals(12, sudokuField.getFieldValue());
    }

    @Test
    void testEquals_invalidClass(){
        SudokuField sudokuField = new SudokuField();

        assertFalse(sudokuField.equals(4));
    }

    @Test
    void testEquals_nullValue(){
        SudokuField sudokuField = new SudokuField();

        assertFalse(sudokuField.equals(null));
    }

    @Test
    void testClone() {
        SudokuField sudokuField = new SudokuField();
        SudokuField clonedField = sudokuField.clone();
        assertEquals(sudokuField.getFieldValue(), clonedField.getFieldValue());
    }

    @Test
    void testHashCode() {
        SudokuField sudokuField = new SudokuField(6);
        SudokuField correctField = new SudokuField(6);
        SudokuField incorrectField = new SudokuField(3);

        assertEquals(sudokuField.hashCode(), correctField.hashCode());
        assertNotEquals(sudokuField.hashCode(), incorrectField.hashCode());
    }

    @Test
    void testToString() {
        SudokuField sudokuField = new SudokuField(7);
        assertEquals("7", sudokuField.toString());
    }

    @Test
    void testEqualsSameObject() {
        SudokuField sudokuField = new SudokuField(7);
        assertEquals(sudokuField, sudokuField);
    }
}