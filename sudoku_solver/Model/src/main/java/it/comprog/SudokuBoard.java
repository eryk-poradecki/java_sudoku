package it.comprog;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.*;

import static it.comprog.SudokuUtils.boxSize;
import static it.comprog.SudokuUtils.gridSize;


public class SudokuBoard implements Serializable {

    private final SudokuField[][] board = new SudokuField[gridSize][gridSize];

    private final SudokuSolver sudokuSolver;

    private List<Map.Entry<SudokuSubscriber, Integer>> sudokuSubscribers = new ArrayList<>();

    SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = new SudokuField();
            }
        }
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int col, int row) {
        return board[col][row].getFieldValue();
    }

    public void set(int col, int row, int value) {
        if (get(col, row) != value) {
            board[col][row].setFieldValue(value);
            notifySubscribers(col, row, value);
        }
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, board[i][row].clone());
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        subscribe(sudokuRow, row);
        return sudokuRow;
    }

    public SudokuColumn getColumn(int col) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        for (int i = 0; i < gridSize; i++) {
            sudokuFields.set(i, board[col][i].clone());
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        subscribe(sudokuColumn, col);
        return sudokuColumn;
    }

    public SudokuBox getBox(int col, int row) {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[gridSize]);
        int boxCol = col - col % boxSize;
        int boxRow = row - row % boxSize;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                sudokuFields.set(i * boxSize + j, board[boxCol + i][boxRow + j].clone());
            }
        }
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);
        subscribe(sudokuBox, boxRow * 3 + boxCol);
        return sudokuBox;
    }

    // checks if a given value can be inserted in a cell
    public boolean canInsertValue(int col, int row, int value) {
        return !getRow(row).contains(value) && !getColumn(col).contains(value) && !getBox(col, row).contains(value);
    }

    public boolean checkBoardValidity() {
        // checks if all rows and columns are valid
        for (int i = 0; i < gridSize; i++) {
            if (!getRow(i).isValid()) {
                return false;
            }
            if (!getColumn(i).isValid()) {
                return false;
            }
        }

        // checks if all boxes are valid
        for (int i = 0; i < gridSize; i += 3) {
            for (int j = 0; j < gridSize; j += 3) {
                if (!getBox(i,j).isValid()) {
                    return false;
                }
            }
        }

        return true;
    }

    private void subscribe(SudokuSubscriber subscriber, int subGrid) {
        Map.Entry<SudokuSubscriber, Integer> sub = new AbstractMap.SimpleEntry<>(subscriber, subGrid);
        sudokuSubscribers.add(sub);
    }

    public void unsubscribe(SudokuSubGrid subGrid) {
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            if (sub.getKey() == subGrid) {
                sudokuSubscribers.remove(sub);
                break;
            }
        }
    }

    private void notifySubscribers(int col, int row, int value) {
        for (Map.Entry<SudokuSubscriber, Integer> sub : sudokuSubscribers) {
            sub.getKey().update(col, row, sub.getValue(), value);
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    @Override
    public String toString() {
        String boardToString = "\n";
        for (int i = 0; i < gridSize; i++) {
            boardToString += "{";
            for (int j = 0; j < gridSize; j++) {
                boardToString += get(i,j);
                if (j != 8) {
                    boardToString += ", ";
                }
            }
            boardToString += "}\n";
        }

        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sudokuBoard", boardToString)
                .append("sudokuSolver", sudokuSolver)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard checkBoard = (SudokuBoard) o;
        return new EqualsBuilder()
                .append(this.board, checkBoard.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 41)
                .append(board)
                .toHashCode();
    }


}
