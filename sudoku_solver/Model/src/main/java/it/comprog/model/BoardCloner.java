package it.comprog.model;

public class BoardCloner {

    SudokuBoard prototypeBoard;

    public BoardCloner(SudokuBoard prototypeBoard) {
        this.prototypeBoard = prototypeBoard;
    }

    public SudokuBoard createNewBoard() {
        return prototypeBoard.clone();
    }
}
