package com.mycompany.sudoku;

public class SudokuController implements Viewable {

    private EnhancedValidator validator;

    public SudokuController() {
        this.validator = new EnhancedValidator();
    }

    @Override
    public String verifyGame(Game game) {
        return validator.verifyGame(game);
    }
    // other methods can be added here as needed

}
