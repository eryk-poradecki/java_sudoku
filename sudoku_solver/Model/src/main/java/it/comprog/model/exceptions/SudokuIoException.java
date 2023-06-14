package it.comprog.model.exceptions;

import java.io.IOException;

public class SudokuIoException extends IOException {

    public SudokuIoException(String message) {
        super(message);
    }
}
