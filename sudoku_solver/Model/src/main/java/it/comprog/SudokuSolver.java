package it.comprog;

import java.io.Serializable;

public interface SudokuSolver extends Serializable {

    void solve(SudokuBoard sudokuBoard);
}
