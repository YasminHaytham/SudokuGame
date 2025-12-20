package com.mycompany.sudoku;

public class ValidatorFactory {

    public static Validator getValidator(int mode, Board board) {
        switch (mode) {
        case 0:
            return new SequentialValidator(board);
        case 3:
            return new Mode3(board);
        case 27:
            return new Mode27(board);
        default:
            throw new IllegalArgumentException("Invalid mode! Use 0, 3, or 27.");
    }
    }
}
