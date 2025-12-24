package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int [][] board;
    private int emptyCells;

    public Game(int[][] board){
        this.board = board;
        countEmptyCells();
    }

    private void countEmptyCells() {
        int count = 0;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    count++;
                }
            }
        }
        this.emptyCells = count;
    }

    public int[][] getBoard(){
        return board;
    }
    
    public void setBoard(int[][] board){
        this.board = board;
        countEmptyCells();
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public void decrementEmptyCells() {
        if (emptyCells > 0) emptyCells--;
    }

    public void incrementEmptyCells() {
        emptyCells++;
    }

    public void setCell(int row, int col, int value) {
        if (board[row][col] == 0 && value != 0) {
            decrementEmptyCells();
        } else if (board[row][col] != 0 && value == 0) {
            incrementEmptyCells();
        }
        board[row][col] = value;
    }

    public int getCell(int row, int col) {
        return board[row][col];
    }
    public int[][] getEmptyCellPositions() {
        List<int[]> positions = new ArrayList<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    positions.add(new int[]{r, c});
                }
            }
        }
        return positions.toArray(new int[0][]);
    }
    
    public int[][] getBoardCopy() {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}