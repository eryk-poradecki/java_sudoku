package it.comprog.view;

import it.comprog.model.SudokuBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.comprog.model.SudokuUtils.gridSize;

public class Difficulty {

    public enum Difficulties {
        EASY("easy"),
        MEDIUM("medium"),
        HARD("hard");

        Difficulties(String s) {
        }
    }

    private static final int DEF_LVL = 6;

    private static final int[] MULT_LVL = {7, 8, 9};

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
                numbers = uniqueRandomNumbers(DEF_LVL * MULT_LVL[1]);
                break;
            }
            case "HARD": {
                numbers = uniqueRandomNumbers(DEF_LVL * MULT_LVL[2]);
                break;
            }
            default: {
                numbers = uniqueRandomNumbers(DEF_LVL * MULT_LVL[0]);
                break;
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            board.set(numbers.get(i) / 9, numbers.get(i) % 9, 0);
        }
        return board;
    }
}
