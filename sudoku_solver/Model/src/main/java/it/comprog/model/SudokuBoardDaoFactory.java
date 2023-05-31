package it.comprog.model;

public interface SudokuBoardDaoFactory {

    Dao<SudokuBoard> getFileDao(String fileName);
}
