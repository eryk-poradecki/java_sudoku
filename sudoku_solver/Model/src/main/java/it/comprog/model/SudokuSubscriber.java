package it.comprog.model;

import java.io.Serializable;

public interface SudokuSubscriber extends Serializable {

    void update(int col, int row, int subGrid, int value);
}
