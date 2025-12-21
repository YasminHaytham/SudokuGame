package com.mycompany.sudoku;

public class NoSolutionException extends Exception {
    public NoSolutionException(String message) {
        super(message);
    }
}