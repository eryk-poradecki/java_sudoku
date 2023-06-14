package it.comprog.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import it.comprog.model.exceptions.SudokuIoException;

import java.nio.file.Paths;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void testReadWrite(@TempDir Path tempDir) {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2;
        SudokuField testField = new SudokuField(8);

        FileSudokuBoardDaoFactory sudokuFactory = new FileSudokuBoardDaoFactory();

        String fileName = Paths.get(tempDir.toString(), "testReadWrite.brd").toString();

        try (FileSudokuBoardDao fileDao = (FileSudokuBoardDao) sudokuFactory.getFileDao(fileName)) {
            fileDao.write(sudokuBoard);
            sudokuBoard2 = fileDao.read();

            fileDao.write(sudokuBoard);
            sudokuBoard2 = fileDao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(sudokuBoard, sudokuBoard2);
    }

    @Test
    void testReadThrowException(@TempDir Path tempDir) {
        FileSudokuBoardDaoFactory sudokuFactory = new FileSudokuBoardDaoFactory();

        String fileName = Paths.get(tempDir.toString(),"testReadThrowException.brd").toString();

        try (FileSudokuBoardDao fileDao = (FileSudokuBoardDao)sudokuFactory.getFileDao(fileName)) {
            assertThrows(SudokuIoException.class, fileDao::read);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}