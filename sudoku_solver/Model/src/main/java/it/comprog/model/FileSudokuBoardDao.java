package it.comprog.model;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    private FileOutputStream fileOutputStream;

    private FileInputStream fileInputStream;

    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        if (fileInputStream == null) {
            try {
              fileInputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (objectInputStream == null) {
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SudokuBoard sudokuBoard;
        try {
            sudokuBoard = (SudokuBoard)objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (fileOutputStream == null) {
            try {
                fileOutputStream = new FileOutputStream(fileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (objectOutputStream == null) {
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            objectOutputStream.writeObject(sudokuBoard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (objectInputStream != null) {
            objectInputStream.close();
        }
        if (objectOutputStream != null) {
            objectOutputStream.close();
        }
    }
}

