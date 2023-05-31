package it.comprog.model;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws ClassNotFoundException, IOException {
        SudokuBoard sudokuBoard;
        try (
                FileInputStream f = new FileInputStream(fileName);
                ObjectInputStream o = new ObjectInputStream(f)
        ) {
            sudokuBoard = (SudokuBoard) o.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw e;
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw e;
            }
        }

        try (
                FileOutputStream f = new FileOutputStream(fileName);
                ObjectOutputStream o = new ObjectOutputStream(f)
        ) {
            o.writeObject(sudokuBoard);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
    }
}

