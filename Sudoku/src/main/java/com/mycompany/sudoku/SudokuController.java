package com.mycompany.sudoku;

import java.io.IOException;

public class SudokuController implements Viewable {

    private final EnhancedValidator validator;
    private final GameStorage storage;
    private final GameGenerator generator;
    private final Catalog catalog;
    private final UndoManager undoManager;

     public SudokuController() {
        this.validator = new EnhancedValidator();
        this.storage = new GameStorage();
        this.generator = new GameGenerator();
        this.catalog= new Catalog();
        this.undoManager = new UndoManager("games");
    }

    @Override
    public String verifyGame(Game game) {
        return validator.verifyGame(game);
    }
    // other methods can be added here as needed
    @Override
    public Catalog getCatalog() {
        return catalog;
    }

    @Override
     public Game getGame(DifficultyEnum level) throws NotFoundException {
        return storage.readGame(level);
    }
    @Override
   public void driveGames(Game sourceGame) throws SolutionInvalidException, IOException {

    generator.generateFromSolved(sourceGame);
}


    @Override
    public int[] solveGame(Game game) throws InvalidGameException {

    String state = validator.verifyGame(game);

    if (!state.equalsIgnoreCase("incomplete")) {
        throw new InvalidGameException(
            "Game must be valid and incomplete to solve. State: " + state
        );
    }

    if (game.getEmptyCells() != 5) {
        throw new InvalidGameException(
            "Solver requires exactly 5 empty cells. Found: " + game.getEmptyCells()
        );
    }

    try {
        
        Solver solver = new Solver(game);

        return solver.solve();

    } catch (IllegalArgumentException e) {
        throw new InvalidGameException(e.getMessage());

    } catch (NoSolutionException e) {
        throw new InvalidGameException("No solution exists for this board");
    }
}
    @Override
    public void logUserAction(String userAction) throws IOException {
         String clean = userAction.replace("(", "").replace(")", "");
        String[] parts = clean.split(",");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid log format: " + userAction);
        }

                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int oldValue = Integer.parseInt(parts[2]);
                int newValue = Integer.parseInt(parts[3]);
                undoManager.logAction(x, y, newValue, oldValue);
                
    }

    }

