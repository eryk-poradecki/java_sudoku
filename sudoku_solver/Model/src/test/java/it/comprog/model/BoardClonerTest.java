package it.comprog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardClonerTest {

    @Test
    void testCreateNewBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        BoardCloner boardCloner = new BoardCloner(sudokuBoard);
        SudokuBoard testBoard = boardCloner.createNewBoard();
        assertNotSame(sudokuBoard, testBoard);
    }
}