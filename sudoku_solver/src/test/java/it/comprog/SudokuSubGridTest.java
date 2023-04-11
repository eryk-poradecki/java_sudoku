package it.comprog;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static it.comprog.SudokuUtils.gridSize;


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

}