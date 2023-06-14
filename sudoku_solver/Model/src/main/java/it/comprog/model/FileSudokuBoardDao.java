package it.comprog.model;

import it.comprog.model.exceptions.SudokuClassNotFoundException;
import it.comprog.model.exceptions.SudokuIoException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws SudokuClassNotFoundException, SudokuIoException {
        SudokuBoard sudokuBoard;
        try (
                FileInputStream f = new FileInputStream(fileName);
                ObjectInputStream o = new ObjectInputStream(f)
        ) {
            sudokuBoard = (SudokuBoard) o.readObject();
        } catch (ClassNotFoundException e) {
            throw new SudokuClassNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new SudokuIoException(e.getMessage());
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws SudokuIoException {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new SudokuIoException(e.getMessage());
            }
        }

        try (
                FileOutputStream f = new FileOutputStream(fileName);
                ObjectOutputStream o = new ObjectOutputStream(f)
        ) {
            o.writeObject(sudokuBoard);
        } catch (IOException e) {
            throw new SudokuIoException(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
    }
}

