package com.mycompany.sudoku;

public class BoardFlyweight {
    private final int[][] baseBoard;
    private int[][] tempBoard;
    private EnhancedValidator validator;
    
    public BoardFlyweight(int[][] board) {
        this.baseBoard = copyBoard(board);
        this.tempBoard = new int[9][9];
        this.validator = new EnhancedValidator();
    }
    
    public boolean isValidWithCombination(int[][] emptyPositions, int[] combination) {
        resetTempBoard();
        
    
        for (int i = 0; i < emptyPositions.length; i++) {
            int row = emptyPositions[i][0];
            int col = emptyPositions[i][1];
            tempBoard[row][col] = combination[i];
        }
        
        return validator.isBoardValid(tempBoard);
    }
    private void resetTempBoard() {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(baseBoard[i], 0, tempBoard[i], 0, 9);
        }
    }
    
    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}