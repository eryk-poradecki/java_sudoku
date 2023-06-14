package it.comprog.model;

import it.comprog.model.exceptions.SudokuClassNotFoundException;
import it.comprog.model.exceptions.SudokuIoException;

public interface Dao<T> {

    T read() throws SudokuIoException, SudokuClassNotFoundException;

    void write(T object) throws SudokuIoException;
}
