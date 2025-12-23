package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnhancedValidator {
    private List<String> errors = new ArrayList<>();
    private int[][] board;

    public GameState validate(Game game) {
        errors.clear();
        this.board = game.getBoard();
        
        checkRowsForDuplicates();
        checkColumnsForDuplicates();
        checkBoxesForDuplicates();

        if (!errors.isEmpty())
            return GameState.INVALID;

        if (game.getEmptyCells() > 0)
            return GameState.INCOMPLETE;

        return GameState.VALID;
    }

    public String verifyGame(Game game) {
        GameState state = validate(game);
        
        if (state == GameState.VALID)
            return "valid";

        if (state == GameState.INCOMPLETE)
            return "incomplete";

        return "invalid " + getInvalidCoordinates();
    }

    private String getInvalidCoordinates() {
        Set<String> coordinates = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0 && !isValidCell(i, j, board[i][j])) {
                    coordinates.add(i + "," + j);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (String coord : coordinates) {
            sb.append(coord).append(" ");
        }
        return sb.toString().trim();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isBoardValid(int[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0 && !isValidCell(i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValidCell(int x, int y, int value) {
        if (value == 0) return true;
        
        for (int col = 0; col < 9; col++) {
            if (col != y && board[x][col] == value) {
                return false;
            }
        }
        
        for (int row = 0; row < 9; row++) {
            if (row != x && board[row][y] == value) {
                return false;
            }
        }
        
        int boxStartRow = (x / 3) * 3;
        int boxStartCol = (y / 3) * 3;
        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
                if (i != x && j != y && board[i][j] == value) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private void checkRowsForDuplicates() {
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[10];
            for (int col = 0; col < 9; col++) {
                int val = board[row][col];
                if (val != 0) {
                    if (seen[val]) {
                        errors.add("Row " + row + " duplicate " + val);
                    }
                    seen[val] = true;
                }
            }
        }
    }
    
    private void checkColumnsForDuplicates() {
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[10];
            for (int row = 0; row < 9; row++) {
                int val = board[row][col];
                if (val != 0) {
                    if (seen[val]) {
                        errors.add("Column " + col + " duplicate " + val);
                    }
                    seen[val] = true;
                }
            }
        }
    }
    
    private void checkBoxesForDuplicates() {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                boolean[] seen = new boolean[10];
                for (int r = boxRow * 3; r < boxRow * 3 + 3; r++) {
                    for (int c = boxCol * 3; c < boxCol * 3 + 3; c++) {
                        int val = board[r][c];
                        if (val != 0) {
                            if (seen[val]) {
                                errors.add("Box (" + boxRow + "," + boxCol + ") duplicate " + val);
                            }
                            seen[val] = true;
                        }
                    }
                }
            }
        }
    }
    
    public boolean isValid(int x, int y, int[][] board) {
        this.board = board;
        return isValidCell(x, y, board[x][y]);
    }
    
    public void RowDuplicateValidator() {
        checkRowsForDuplicates();
    }
    
    public void ColumnDuplicateValidator() {
        checkColumnsForDuplicates();
    }
    
    public void BoxDuplicateValidator() {
        checkBoxesForDuplicates();
    }
}