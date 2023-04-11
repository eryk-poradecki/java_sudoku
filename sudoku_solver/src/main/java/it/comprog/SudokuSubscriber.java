package it.comprog;

public interface SudokuSubscriber {

    void update(int col, int row, int subGrid, int value);
}
