package it.comprog;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static it.comprog.SudokuUtils.gridSize;

class SudokuBoardTest {

    private static final int boxSize = 3;

    @Test
    public void testGet_withoutSetValue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(0,sudokuBoard.get(5,4));
    }

    @Test
    public void testGet_withSetValue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.set(3,4,7);
        sudokuBoard.set(3,4,7); //set twice to cover an if statement
        assertEquals(7,sudokuBoard.get(3,4));
    }

    @Test
    public void testGetRow_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[] expectedValues = {4, 6, 8, 9, 7, 2, 3, 1, 5};
        List<SudokuField> expectedFields = Arrays.asList(new SudokuField[gridSize]);

        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(i,4, expectedValues[i]);
            expectedFields.set(i, new SudokuField(expectedValues[i]));
        }

        SudokuRow expectedRow = new SudokuRow(expectedFields);

        for (int i = 0; i < gridSize; i ++) {
            assertEquals(expectedRow.get(i).getFieldValue(), sudokuBoard.getRow(4).get(i).getFieldValue());
        }
    }

    @Test
    public void testGetColumn_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[] expectedValues = {4, 6, 8, 9, 7, 2, 3, 1, 5};
        List<SudokuField> expectedFields     = Arrays.asList(new SudokuField[gridSize]);

        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(6,i, expectedValues[i]);
            expectedFields.set(i, new SudokuField(expectedValues[i]));
        }

        SudokuColumn expectedColumn = new SudokuColumn(expectedFields);

        for (int i = 0; i < gridSize; i ++) {
            assertEquals(expectedColumn.get(i).getFieldValue(), sudokuBoard.getColumn(6).get(i).getFieldValue());
        }
    }

    @Test
    public void testGetBox_withSetValues() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[] expectedValues = {4, 6, 8, 9, 7, 2, 3, 1, 5};
        List<SudokuField> expectedFields = Arrays.asList(new SudokuField[gridSize]);

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j ++) {
                sudokuBoard.set(i,j, expectedValues[i * boxSize + j]);
                expectedFields.set(i * boxSize + j, new SudokuField(expectedValues[i * boxSize + j]));
            }
        }

        SudokuBox expectedBox = new SudokuBox(expectedFields);

        for (int i = 0; i < gridSize; i ++) {
            assertEquals(expectedBox.get(i).getFieldValue(), sudokuBoard.getBox(0,0).get(i).getFieldValue());
        }
    }

    @Test
    public void testCheckBoardValidity_checkRow_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(3, i, i + 1);
        }
        assertTrue(sudokuBoard.checkBoardValidity());
        sudokuBoard.set(3,4,9);
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_checkColumn_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(i, 6, i + 1);
        }
        assertTrue(sudokuBoard.checkBoardValidity());
        sudokuBoard.set(1,6,9);
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_checkBox_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < boxSize; i++) {
            for(int j = 0; j < boxSize; j++) {
               sudokuBoard.set(i, j, i * 3 + j + 1);
            }
        }
        assertTrue(sudokuBoard.checkBoardValidity());
        sudokuBoard.set(1,2,9);
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_checkBox_invalidValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++){
                sudokuBoard.set(i,j, i * boxSize + j + 1);
            }
        }
        sudokuBoard.set(0,0,9);
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_emptyBoard(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertTrue(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_invalidRow(){
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++) {
            sudoku.set(i, 0, i + 1);
        }
        sudoku.set(0, 0, sudoku.get(3,0));
        assertFalse(sudoku.checkBoardValidity());

    }

    @Test
    public void testCheckBoardValidity_invalidColumn(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++) {
            sudokuBoard.set(0, i, i + 1);
        }
        sudokuBoard.set(0, 0, 2);
        assertFalse(sudokuBoard.checkBoardValidity());
        sudokuBoard.set(0, 0, 1);
        assertTrue(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_invalidBox(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                sudokuBoard.set(i, j, i * boxSize + j + 1);
            }
        }
        sudokuBoard.set(1, 1, 9);
        assertFalse(sudokuBoard.checkBoardValidity());
        sudokuBoard.set(1, 1, 5);
        assertTrue(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCanInsertValue_validNumbers(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 1; i <= gridSize; i++) {
            assertTrue(sudokuBoard.canInsertValue(0,0,i));
        }
    }

    @Test
    public void testNotify(){

        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());

        SudokuRow row = sudoku.getRow(1);
        SudokuColumn column = sudoku.getColumn(1);
        SudokuBox box = sudoku.getBox(0, 0);
        SudokuBox box2 = sudoku.getBox(8, 8);

        assertTrue(row.isValid());
        assertTrue(column.isValid());
        assertTrue(box.isValid());
        assertTrue(box2.isValid());

        sudoku.set(0, 1, 1);
        sudoku.set(1, 0, 1);
        sudoku.set(1, 1, 1);

        assertFalse(row.isValid());
        assertFalse(column.isValid());
        assertFalse(box.isValid());
        assertTrue(box2.isValid());

        sudoku.unsubscribe(row);
        sudoku.unsubscribe(row);
        sudoku.unsubscribe(column);
        sudoku.unsubscribe(box);

        sudoku.set(0, 1, 0);
        sudoku.set(1, 0, 0);

        assertFalse(row.isValid());
        assertFalse(column.isValid());
        assertFalse(box.isValid());
    }
}
