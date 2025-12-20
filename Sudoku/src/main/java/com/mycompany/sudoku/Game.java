package com.mycompany.sudoku;

import java.io.IOException;

class Game {
    private int[][] board;
    private String
     filename;
    public Game(int[][] board, String filename) {
        // IMPORTANT: DON'T COPY THE BOARD BY VALUE
        // USE REFERENCES
        this.board = board;
        this.filename = filename;
    }
    public Board getBoard() throws IOException {
        return new Board(this.filename);
    }
}

