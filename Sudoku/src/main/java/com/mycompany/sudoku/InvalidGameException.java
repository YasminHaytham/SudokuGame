package com.mycompany.sudoku;

public class InvalidGameException extends Exception {
    public InvalidGameException(String message) {
        super(message);
    }
}