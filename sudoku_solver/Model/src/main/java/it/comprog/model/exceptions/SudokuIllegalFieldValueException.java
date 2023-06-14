package it.comprog.model.exceptions;

import java.util.Locale;

public class SudokuIllegalFieldValueException extends IllegalArgumentException {

    public SudokuIllegalFieldValueException() {
        super(Messages.getMessage("SudokuIllegalFieldValueException", Locale.getDefault()));
    }
}
