package it.comprog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    private static final int gridSize = 9;

    private static final int boxSize = 3;

    private SudokuBoard swapColumns(SudokuBoard solvedSudokuBoard){
        int[] temp4Col = solvedSudokuBoard.getColumn(4);
        int[] temp6Col = solvedSudokuBoard.getColumn(6);
        for (int i = 0; i < gridSize; i++){
            solvedSudokuBoard.set(i,4, temp6Col[i]);
            solvedSudokuBoard.set(i,6, temp4Col[i]);
        }
        return solvedSudokuBoard;
    }

    @Test
    public void testGet_withoutSetValue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(0,sudokuBoard.get(5,4));
    }

    @Test
    public void testGet_withSetValue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.set(3,4,7);
        assertEquals(7,sudokuBoard.get(3,4));
    }

    @Test
    public void testGetRow_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[] expectedRow = {4, 6, 8, 9, 7, 2, 3, 1, 5};
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(4,i, expectedRow[i]);
        }
        assertArrayEquals(expectedRow, sudokuBoard.getRow(4));
    }

    @Test
    public void testGetColumn_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[] expectedColumn = {4, 6, 8, 9, 7, 2, 3, 1, 5};
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(i,6, expectedColumn[i]);
        }
        assertArrayEquals(expectedColumn, sudokuBoard.getColumn(6));
    }

    @Test
    public void testGetBox_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int[][] expectedBox = { {4, 6, 8},
                              {9, 7, 2},
                              {3, 1, 5} };
        for (int i = 0; i < boxSize; i++){
            for (int j = 0; j < boxSize; j++){
                sudokuBoard.set(i, j, expectedBox[i][j]);
            }

        }
        assertArrayEquals(expectedBox, sudokuBoard.getBox(2,1));
    }
    @Test
    public void testCheckArrayValidity_invalidArray(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard.set(0,4,10);
        assertFalse(sudokuBoard.checkArrayValidity(sudokuBoard.getColumn(4)));
        sudokuBoard.set(4,0,-10);
        assertFalse(sudokuBoard.checkArrayValidity(sudokuBoard.getRow(4)));

    }
    @Test
    public void testCheckArrayValidity_checkRow_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(3, i, i + 1);
        }
        assertTrue(sudokuBoard.checkArrayValidity(sudokuBoard.getRow(3)));
        sudokuBoard.set(3,4,9);
        assertFalse(sudokuBoard.checkArrayValidity(sudokuBoard.getRow(3)));
    }

    @Test
    public void testCheckArrayValidity_checkColumn_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < gridSize; i++){
            sudokuBoard.set(i, 6, i + 1);
        }
        assertTrue(sudokuBoard.checkArrayValidity(sudokuBoard.getColumn(6)));
        sudokuBoard.set(1,6,9);
        assertFalse(sudokuBoard.checkArrayValidity(sudokuBoard.getColumn(6)));
    }

    @Test
    public void testCheckArrayValidity_checkBox_withSetValues(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for(int i = 0; i < boxSize; i++) {
            for(int j = 0; j < boxSize; j++) {
               sudokuBoard.set(i, j, i * 3 + j + 1);
            }
        }
        assertTrue(sudokuBoard.checkArrayValidity(sudokuBoard.boxToArray(sudokuBoard.getBox(1,2))));
        sudokuBoard.set(1,2,9);
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_emptyBoard(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_invalidRow(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard.set(0,3,sudokuBoard.get(0,5));
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_invalidColumn(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard.set(2,0,sudokuBoard.get(5,0));
        assertFalse(sudokuBoard.checkBoardValidity());
    }

    @Test
    public void testCheckBoardValidity_invalidBox(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard swappedBoard;
        sudokuBoard.solveGame();
        swappedBoard = swapColumns(sudokuBoard);
        assertFalse(swappedBoard.checkBoardValidity());
    }
}
