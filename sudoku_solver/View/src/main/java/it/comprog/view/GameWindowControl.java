package it.comprog.view;

import it.comprog.model.BacktrackingSudokuSolver;
import it.comprog.model.BoardCloner;
import it.comprog.model.SudokuBoard;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import static it.comprog.model.SudokuUtils.gridSize;

public class GameWindowControl {
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard sudokuBoardCopy;
    private Difficulty difficulty = new Difficulty();

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private void initialize() { //NOPMD
        sudokuBoard.solveGame();
        BoardCloner cloner = new BoardCloner(sudokuBoard);
        sudokuBoardCopy = cloner.createNewBoard();
        sudokuBoardCopy = difficulty.zeroNFields(sudokuBoardCopy, MenuWindowControl.getChosenDifficulty());
        fill();
    }

    private void fill() {
        for (int i = 0; i < gridSize; i++) {
            for (int o = 0; o < gridSize; o++) {
                TextField text = new TextField();
                text.setPrefSize(100, 100);
                text.setFont(Font.font(20));
                // Only single characters can be inserted into sudoku fields
                text.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() > 1) {
                        String copy = text.getText(0,1);
                        text.setText(copy);
                    }
                });
                text.setStyle(
                        "-fx-text-inner-color: grey;"
                                + "-fx-background-color: black, white;"
                                + "-fx-background-insets: 5px;");
                text.setAlignment(Pos.CENTER);
                if (sudokuBoardCopy.get(i, o) != 0) {
                    text.setDisable(true);
                    text.setStyle(
                            "-fx-opacity: 1;"
                                    + "-fx-text-inner-color: black;"
                                    + "-fx-background-insets: 5px;");
                }
                text.setText(String.valueOf(sudokuBoardCopy.get(i, o)));
                sudokuGrid.add(text, i, o);
            }
        }
    }
}
