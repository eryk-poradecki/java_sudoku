package it.comprog.model;

public interface SudokuSubscriber {

    void update(int col, int row, int subGrid, int value);
}
