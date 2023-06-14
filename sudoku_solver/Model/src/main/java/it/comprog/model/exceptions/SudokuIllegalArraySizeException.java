package it.comprog.model.exceptions;

import java.util.Locale;

public class SudokuIllegalArraySizeException extends IllegalArgumentException {

    public SudokuIllegalArraySizeException() {
        super(Messages.getMessage("SudokuIllegalArraySizeException", Locale.getDefault()));
    }
}
