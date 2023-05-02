package it.comprog;

public interface SudokuBoardDaoFactory {

    Dao<SudokuBoard> getFileDao(String fileName);
}
