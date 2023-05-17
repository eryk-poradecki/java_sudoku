package it.comprog.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static it.comprog.model.SudokuUtils.gridSize;


class SudokuSubGridTest {

    @Test
    void testGet() {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);

        int[] expectedValues = {9,2,3,1,4,6,5,7,8};

        for (int i = 0; i < gridSize; i ++) {
            sudokuFields.set(i, new SudokuField(expectedValues[i]));
        }

        SudokuRow expectedRow = new SudokuRow(sudokuFields);

        for (int i = 0; i < gridSize; i++) {
            assertEquals(expectedValues[i], expectedRow.get(i).getFieldValue());
        }
    }

    @Test
    void testVerify_validValues() {
        int[] validValues = {9,2,3,1,4,6,5,7,8};

        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);

        for (int i = 0; i < gridSize; i ++){
            sudokuFields.set(i, new SudokuField(validValues[i]));
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);

        assertTrue(sudokuRow.verify());
        assertTrue(sudokuColumn.verify());
        assertTrue(sudokuBox.verify());
    }

    @Test
    void testVerify_invalidValues() {
        int[] validValues = {9,3,3,4,4,6,5,7,8};

        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);

        for (int i = 0; i < gridSize; i ++){
            sudokuFields.set(i, new SudokuField(validValues[i]));
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);

        assertFalse(sudokuRow.verify());
        assertFalse(sudokuColumn.verify());
        assertFalse(sudokuBox.verify());
    }

    @Test
    void testHashCode() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] values2 = {3, 2, 1, 4, 5, 6, 9, 8, 7};

        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        List<SudokuField> sudokuFields2 = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, new SudokuField(values[i]));
            sudokuFields2.set(i, new SudokuField(values2[i]));
        }

        SudokuRow row = new SudokuRow(sudokuFields);
        SudokuRow correctRow = new SudokuRow(sudokuFields);
        SudokuRow incorrectRow = new SudokuRow(sudokuFields2);

        assertEquals(row.hashCode(), correctRow.hashCode());
        assertNotEquals(row.hashCode(), incorrectRow.hashCode());
    }

    @Test
    void testToString() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, new SudokuField(expected[i]));
        }

        SudokuRow row = new SudokuRow(sudokuFields);

        sudokuFields.set(1, new SudokuField(3));
        SudokuRow differentRow = new SudokuRow(sudokuFields);

        String expectedString = "SudokuRow[sudokuFields=[1, 2, 3, 4, 5, 6, 7, 8, 9],valid=true]";

        assertEquals(expectedString, row.toString());
        assertNotEquals(row.toString(), differentRow.toString());
    }

    @Test
    void testEquals() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] values2 = {3, 2, 1, 4, 5, 6, 9, 8, 7};

        List<SudokuField> expectedFields = Arrays.asList(new SudokuField[gridSize]);
        List<SudokuField> unexpectedFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            expectedFields.set(i, new SudokuField(values[i]));
            unexpectedFields.set(i, new SudokuField(values2[i]));
        }

        SudokuRow row = new SudokuRow(expectedFields);
        SudokuRow correctRow = new SudokuRow(expectedFields);
        SudokuRow incorrectRow = new SudokuRow(unexpectedFields);
        SudokuRow nullRow = null;
        SudokuColumn differentClassTest = new SudokuColumn(expectedFields);

        assertEquals(row, row);
        assertEquals(row, correctRow);
        assertNotEquals(row, incorrectRow);
        assertNotEquals(row, nullRow);
        assertNotEquals(row, differentClassTest);
    }

    @Test
    public void testClone() {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for(int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, new SudokuField(i));
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);

        SudokuRow cloneRow = sudokuRow.clone();
        SudokuColumn cloneColumn = sudokuColumn.clone();
        SudokuBox cloneBox = sudokuBox.clone();

        assertNotSame(sudokuRow, cloneRow);
        assertEquals(sudokuRow, cloneRow);

        sudokuRow.set(3,5);
        cloneRow.set(3, 8);

        assertNotEquals(cloneRow.get(3).getFieldValue(), sudokuRow.get(3).getFieldValue());
        assertNotEquals(cloneRow, sudokuRow);
    }

}