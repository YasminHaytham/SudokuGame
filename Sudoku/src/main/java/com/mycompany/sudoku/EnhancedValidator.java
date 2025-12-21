package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.List;

public class EnhancedValidator {

    private List<String> errors = new ArrayList<>();

    public GameState validate(Game game) {
        errors.clear();
        int[][] board = game.getBoard();
        checkRows(board);
        checkColumns(board);
        checkBoxes(board);

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

        return formatErrors();
    }

    private String formatErrors() {
        StringBuilder sb = new StringBuilder("invalid ");
        for (String e : errors) {
            sb.append(e).append(" ");
        }
        return sb.toString().trim();
    }

    private void checkRows(int[][] board) {
        for (int r = 0; r < 9; r++) {
            boolean[] seen = new boolean[10];
            for (int c = 0; c < 9; c++) {
                int v = board[r][c];
                if (v != 0) {
                    if (seen[v]) {
                        errors.add("(r" + r + ")");
                    } else {
                        seen[v] = true;
                    }
                }
            }
        }
    }

    private void checkColumns(int[][] board) {
        for (int c = 0; c < 9; c++) {
            boolean[] seen = new boolean[10];
            for (int r = 0; r < 9; r++) {
                int v = board[r][c];
                if (v != 0) {
                    if (seen[v]) {
                        errors.add("(c" + c + ")");
                    } else {
                        seen[v] = true;
                    }
                }
            }
        }
    }

    private void checkBoxes(int[][] board) {
        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {

                boolean[] seen = new boolean[10];

                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        int v = board[r][c];

                        if (v != 0) {
                            if (seen[v]) {
                                errors.add("(b" + br + "," + bc + ")");
                            } else {
                                seen[v] = true;
                            }
                        }
                    }
                }

            }
        }
    }
    public List<String> getErrors() {
        return errors;
    }

    public boolean isValidBoard(int[][] board) {
        errors.clear();
        checkRows(board);
        checkColumns(board);
        checkBoxes(board);
        return errors.isEmpty();
    }
        // NEW METHOD: Just check if valid without error collection
    public boolean isBoardValid(int[][] board) {
        // Simplified check without collecting errors
        return checkRowsSimple(board) && checkColumnsSimple(board) && checkBoxesSimple(board);
    }
    
    private boolean checkRowsSimple(int[][] board) {
        for (int r = 0; r < 9; r++) {
            boolean[] seen = new boolean[10];
            for (int c = 0; c < 9; c++) {
                int v = board[r][c];
                if (v != 0 && seen[v]) return false;
                if (v != 0) seen[v] = true;
            }
        }
        return true;
    }
    
    private boolean checkColumnsSimple(int[][] board) {
        for (int c = 0; c < 9; c++) {
            boolean[] seen = new boolean[10];
            for (int r = 0; r < 9; r++) {
                int v = board[r][c];
                if (v != 0 && seen[v]) return false;
                if (v != 0) seen[v] = true;
            }
        }
        return true;
    }
    
    private boolean checkBoxesSimple(int[][] board) {
        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {
                boolean[] seen = new boolean[10];
                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        int v = board[r][c];
                        if (v != 0 && seen[v]) return false;
                        if (v != 0) seen[v] = true;
                    }
                }
            }
        }
        return true;
    }
}
