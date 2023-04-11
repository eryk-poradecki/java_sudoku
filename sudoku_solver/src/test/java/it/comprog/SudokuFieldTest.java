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
}