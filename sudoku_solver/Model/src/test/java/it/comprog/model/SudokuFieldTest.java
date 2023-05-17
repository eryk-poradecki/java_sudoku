package it.comprog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    public void testSetGetValue_validValue() {
        SudokuField sudokuField = new SudokuField();

        assertEquals(0, sudokuField.getFieldValue());
        sudokuField.setFieldValue(9);
        assertEquals(9, sudokuField.getFieldValue());
    }

    @Test
    public void testSetGetValue_invalidValue(){
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(-3);
        assertNotEquals(-3, sudokuField.getFieldValue());
        sudokuField.setFieldValue(12);
        assertNotEquals(12, sudokuField.getFieldValue());
    }

    @Test
    public void testEquals_invalidClass(){
        SudokuField sudokuField = new SudokuField();

        assertFalse(sudokuField.equals(4));
    }

    @Test
    public void testEquals_nullValue(){
        SudokuField sudokuField = new SudokuField();

        assertFalse(sudokuField.equals(null));
    }

    @Test
    public void testClone() {
        SudokuField sudokuField = new SudokuField();
        SudokuField clonedField = sudokuField.clone();
        assertEquals(sudokuField.getFieldValue(), clonedField.getFieldValue());
    }

    @Test
    public void testHashCode() {
        SudokuField sudokuField = new SudokuField(6);
        SudokuField correctField = new SudokuField(6);
        SudokuField incorrectField = new SudokuField(3);

        assertEquals(sudokuField.hashCode(), correctField.hashCode());
        assertNotEquals(sudokuField.hashCode(), incorrectField.hashCode());
    }

    @Test
    public void testToString() {
        SudokuField sudokuField = new SudokuField(7);
        assertEquals("7", sudokuField.toString());
    }

    @Test
    public void testEqualsSameObject() {
        SudokuField sudokuField = new SudokuField(7);
        assertEquals(sudokuField, sudokuField);
    }

    @Test
    public void testCompare() {
        SudokuField sudokuField = new SudokuField(5);
        SudokuField lessField = new SudokuField(1);
        SudokuField moreField = new SudokuField(9);
        SudokuField equalField = new SudokuField(5);

        assertTrue(sudokuField.compareTo(lessField) > 0);
        assertTrue(sudokuField.compareTo(moreField) < 0);
        assertEquals(sudokuField.compareTo(equalField), 0);
        assertThrows(NullPointerException.class, () -> sudokuField.compareTo(null));
    }
}