package it.comprog.model;

public class FileSudokuBoardDaoFactory implements SudokuBoardDaoFactory {

    @Override
    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
