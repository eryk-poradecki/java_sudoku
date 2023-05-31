package it.comprog.view;

import it.comprog.model.SudokuBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static it.comprog.model.SudokuUtils.gridSize;

public class Difficulty {

    public enum Difficulties {
        EASY("Easy", 6 * 7),
        MEDIUM("Medium", 6 * 8),
        HARD("Hard", 6 * 9);

        String level;
        int fieldsToEmpty;
        private ResourceBundle resourceBundle = Main.getResourceBundle();

        Difficulties(String s, int i) {
            level = s;
            fieldsToEmpty = i;
        }

        String getLevel() {
            return level;
        }

        String getLocaleLevel() {
            return resourceBundle.getString(getLevel());
        }

        int getFieldsToEmpty() {
            return fieldsToEmpty;
        }
    }


    private List<Integer> uniqueRandomNumbers(int n) {
        List<Integer> setNumbers = new ArrayList<>();
        for (int i = 0; i < gridSize * gridSize; i++) {
            setNumbers.add(i);
        }
        Collections.shuffle(setNumbers);
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randomNumbers.add(setNumbers.get(i));
        }
        return randomNumbers;
    }

    public SudokuBoard zeroNFields(SudokuBoard board, String diff) {
        List<Integer> numbers;
        switch (diff) {
            case "MEDIUM": {
                numbers = uniqueRandomNumbers(Difficulties.MEDIUM.getFieldsToEmpty());
                break;
            }
            case "HARD": {
                numbers = uniqueRandomNumbers(Difficulties.HARD.getFieldsToEmpty());
                break;
            }
            default: {
                numbers = uniqueRandomNumbers(Difficulties.EASY.getFieldsToEmpty());
                break;
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            board.set(numbers.get(i) / 9, numbers.get(i) % 9, 0);
        }
        return board;
    }
}
