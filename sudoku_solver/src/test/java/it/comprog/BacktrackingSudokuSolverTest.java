package it.comprog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    public void testCheckBoardValidity_afterSolving() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertTrue(sudokuBoard.checkBoardValidity());
    }

    @Test
    void testSolveGame_twoBoards_notEqual() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        assertNotSame(sudokuBoard1, sudokuBoard2);
        assertNotSame(sudokuBoard1.getBox(3,4), sudokuBoard2.getBox(3,4));
    }

    @Test
    void testHashCode() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver sudokuSolver2 = new BacktrackingSudokuSolver();

        assertEquals(sudokuSolver.hashCode(), sudokuSolver2.hashCode());
    }

    @Test
    void testToString() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        assertEquals("BacktrackingSudokuSolver[]", sudokuSolver.toString());
    }

    @Test
    void testEquals() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver correctSolver = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver nullSolver = null;
        Object differentClassTest = new Object();

        assertEquals(sudokuSolver, sudokuSolver);
        assertEquals(sudokuSolver, correctSolver);
        assertNotEquals(sudokuSolver, nullSolver);
        assertNotEquals(sudokuSolver, differentClassTest);

    }

}