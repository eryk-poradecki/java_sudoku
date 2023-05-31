package it.comprog.model;

import java.io.Serializable;

public interface SudokuSolver extends Serializable, Cloneable {

    void solve(SudokuBoard sudokuBoard);

    SudokuSolver clone();
}
