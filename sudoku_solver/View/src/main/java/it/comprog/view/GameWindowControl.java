package it.comprog.view;

import it.comprog.model.*;
import it.comprog.model.SudokuBoard;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

import static it.comprog.model.SudokuUtils.gridSize;

public class GameWindowControl {
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard sudokuBoardCopy;
    private Difficulty difficulty = new Difficulty();

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private void initialize() throws NoSuchMethodException { //NOPMD
        sudokuBoard.solveGame();
        BoardCloner cloner = new BoardCloner(sudokuBoard);
        sudokuBoardCopy = cloner.createNewBoard();
        sudokuBoardCopy = difficulty.zeroNFields(sudokuBoardCopy, MenuWindowControl.getChosenDifficulty());
        fill();
    }

    @FXML
    public void saveToFile(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save sudoku board");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(file.getAbsolutePath());
                fileSudokuBoardDao.write(sudokuBoardCopy);
            }
            System.out.println("Board state saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBoardFromFile() {
        int counter = 0;
        for (Node node : sudokuGrid.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                textField.setText(String.valueOf(sudokuBoardCopy.get(counter / 9, counter % 9)));
                counter++;
            }
        }
    }

    public void openFromFile(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open sudoku board");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            File f = fileChooser.showOpenDialog(stage);

            if (f != null) {
                FileSudokuBoardDao file = new FileSudokuBoardDao(f.getAbsolutePath());
                sudokuBoardCopy = file.read();
                updateBoardFromFile();
            }
            System.out.println("Board state loaded from file");
        } catch (IOException e) {
            System.out.println("Couldn't load file from board");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("File doesn't contain a valid sudoku board");
            e.printStackTrace();
        }
    }

    private void fill() throws NoSuchMethodException {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                TextField text = new TextField();
                text.setPrefSize(100, 100);
                text.setFont(Font.font(20));
                text.setStyle(
                        "-fx-text-inner-color: grey;"
                                + "-fx-background-color: black, white;"
                                + "-fx-background-insets: 5px;");
                text.setAlignment(Pos.CENTER);
                if (sudokuBoardCopy.get(i, j) != 0) {
                    text.setDisable(true);
                    text.setStyle(
                            "-fx-opacity: 1;"
                                    + "-fx-text-inner-color: black;"
                                    + "-fx-background-insets: 5px;");
                }

                StringConverter<Integer> stringConverter = new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer integer) {
                        if (integer == null || integer.intValue() == 0) {
                            return "";
                        }
                        return integer.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        if (string == null || string.isEmpty()) {
                            return 0;
                        }
                        return Integer.parseInt(string);
                    }
                };

                UnaryOperator<TextFormatter.Change> textFilter = c -> {
                    if (c.getText().matches("[1-9]")) {
                        c.setRange(0, text.getText().length());
                        return c;
                    } else {
                        if (c.getText().isEmpty()) {
                            return c;
                        }
                    }
                    return null;
                };

                TextFormatter<Integer> textFormatter = new TextFormatter<Integer>(stringConverter, 0, textFilter);

                text.setTextFormatter(textFormatter);

                JavaBeanIntegerProperty integerProperty =
                        JavaBeanIntegerPropertyBuilder
                                .create()
                                .bean(sudokuBoardCopy.getField(i,j))
                                .name("value")
                                .getter("getFieldValue")
                                .setter("setFieldValue")
                                .build();

                text.setText(String.valueOf(sudokuBoardCopy.get(i,j)));
                Bindings.bindBidirectional(text.textProperty(), integerProperty, new NumberStringConverter());
                sudokuGrid.add(text, j, i);
            }
        }
    }
}
