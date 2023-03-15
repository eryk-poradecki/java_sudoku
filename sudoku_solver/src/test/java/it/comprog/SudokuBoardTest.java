package it.comprog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

class SudokuBoardTest {

    int gridSize = 9;
    int boxSize = 3;

    int[][] exampleBoard = { { 4,3,5,2,6,9,7,8,1 },
                            { 6,8,2,5,7,1,4,9,3 },
                            { 1,9,7,8,3,4,5,6,2 },
                            { 8,2,6,1,9,5,3,4,7 },
                            { 3,7,4,6,8,2,9,1,5 },
                            { 9,5,1,7,4,3,6,2,8 },
                            { 5,1,9,3,2,6,8,7,4 },
                            { 2,4,8,9,5,7,1,3,6 },
                            { 7,6,3,4,1,8,2,5,9 } };


    public boolean checkArrayValidity(int[] array) {
        for(int i = 0; i < gridSize; i++) {
            if(array[i] <= 0 || array[i] > 9) {
                return false;
            }
        }
        Set<Integer> s = new HashSet<Integer>();
        for(int i : array) {
            if (s.contains(i)) return false;
            s.add(i);
        }
        return true;
    }

    public int[] boxToArray(int[][] box) {
        int[] result = new int[9];
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                result[i * 3 + j] = box[i][j];
            }
        }
        return result;
    }

    @Test
    public void testGet_preparedBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(exampleBoard);
        assertEquals(4,sudokuBoard.get(5,4));
    }

    @Test
    void testSet_preparedBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(exampleBoard);
        sudokuBoard.set(4,4,9);
        assertEquals(9, sudokuBoard.get(4,4));
    }

    @Test
    void testGetRow_preparedBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(exampleBoard);
        assertArrayEquals(exampleBoard[3], sudokuBoard.getRow(3));
    }

    @Test
    void testGetColumn_preparedBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(exampleBoard);

        int[] expectedColumn = new int[gridSize];
        for(int i = 0; i < gridSize; i ++) {
            expectedColumn[i] = sudokuBoard.get(i,4);
        }
        assertArrayEquals(expectedColumn, sudokuBoard.getColumn(4));
    }

    @Test
    void testGetBox_preparedBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(exampleBoard);
        int[][] expectedBox = { {8,7,4},
                               {1,3,6},
                               {2,5,9} };

        assertArrayEquals(expectedBox, sudokuBoard.getBox(8,8));
    }

    @Test
    void testFillBoard_randomBoard_Valid() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        for(int i = 0; i < gridSize; i++) {
            assertTrue(checkArrayValidity(sudokuBoard.getRow(i)));
            assertTrue(checkArrayValidity(sudokuBoard.getColumn(i)));
        }

        for(int i = 0; i < boxSize; i++) {
            for(int j = 0; j < boxSize; j++) {
                assertTrue(checkArrayValidity(boxToArray(sudokuBoard.getBox(i,j))));
            }
        }
    }

    @Test
    void testFillBoard_randomBoards_notEqual() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();

        sudokuBoard1.fillBoard();
        sudokuBoard2.fillBoard();

        assertNotSame(sudokuBoard1, sudokuBoard2);
        assertNotSame(sudokuBoard1.getBox(3,4), sudokuBoard2.getBox(3,4));
    }
}