package com.mycompany.sudoku;

import java.io.IOException;

public class SudokuController implements Viewable {

    private final EnhancedValidator validator;
    private final GameStorage storage;
    private final GameGenerator generator;
    private final Catalog catalog;
    private UndoManager undoManager;
    
    public SudokuController() {

        this.validator = new EnhancedValidator();
        this.storage = new GameStorage();
        this.generator = new GameGenerator();
        this.catalog = new Catalog();
        this.undoManager = null; 
    }

private void initializeUndoManager() {
    try {
        String gameFolderPath = storage.getCurrentGameFolderPath();
        this.undoManager = new UndoManager(gameFolderPath);
    } catch (Exception e) {
        System.err.println("Failed to initialize undo manager: " + e.getMessage());
    }
}
    

    @Override
    public String verifyGame(Game game) {
        String result = validator.verifyGame(game);

        boolean isComplete = game.getEmptyCells() == 0;

        if (isComplete && "valid".equals(result)) {
            handleCVGame(game);
            return "valid - congratulations!";

        } else if (isComplete && result.startsWith("invalid")) {
            
            return "Complete but invalid";

        } else if (!isComplete && "incomplete".equals(result)) {
            
            return "incomplete";

        } else if (!isComplete && result.startsWith("invalid")) {
            return result;

        } else {
            return result;
        }
    }

    private String handleCVGame(Game game) {
       
        try {
            storage.getCurrentGame();
        } catch (NotFoundException e) {
             e.printStackTrace();
        }

        DifficultyEnum currentDifficulty = storage.getCurrentDifficulty();
        try {
            boolean deletedFromFolder = storage.deleteGameFromFolder(currentDifficulty);

            if (!deletedFromFolder) {
                System.err.println("Warning: Could not delete from difficulty folder");
            }
            boolean deletedCurrent = storage.deleteCurrentGame();

            if (!deletedCurrent) {
                System.err.println("Warning: Could not delete from incomplete folder");
            }

            return "valid - Congratulations! Puzzle solved!";

        } catch (Exception e) {
            return "valid - Game solved but cleanup failed: " + e.getMessage();
        }
    }

    @Override
    public Catalog getCatalog() {
        return catalog;
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
    public void abandonCurrentGame() throws IOException, NotFoundException {
        storage.getCurrentGame();
        boolean deleted = storage.deleteCurrentGame();

        if (!deleted) {
            throw new IOException("Failed to delete current game");
        }
    }
        @Override
    public Game getGame(DifficultyEnum level) throws NotFoundException {
        Game game;
        if (level == null) {
            game = storage.ReadCurrentGame();
        } else {
            game = storage.readGame(level);
        }
        
     
        
        return game;
    }
    
    
@Override
public void logUserAction(String userAction) throws IOException {
    if (undoManager == null) {
        initializeUndoManager();
    }

    
    String clean = userAction.replace("(", "").replace(")", "");
    String[] parts = clean.split(",");

    int x = Integer.parseInt(parts[0]);
    int y = Integer.parseInt(parts[1]);
    int newValue = Integer.parseInt(parts[2]);
    int oldValue = Integer.parseInt(parts[3]);

    
    undoManager.logAction(x, y, newValue, oldValue);

    
    Game currentGame;
    try {
        currentGame = storage.getCurrentGame();
        currentGame.getBoard()[x][y] = newValue;
        storage.saveCurrentGame(currentGame);
    } catch (NotFoundException e) {
        e.printStackTrace();
    }
}

    
   
 @Override
    public Game undoLastAction() throws IOException {
        if (undoManager == null) {
            throw new IOException("No undo manager initialized");
        }
        
        if (!undoManager.canUndo()) {
            throw new IOException("No actions to undo");
        }
        
        try {
            Game currentGame = storage.getCurrentGame();
            if (currentGame == null) {
                throw new IOException("No game loaded");
            }
            
           
            undoManager.undo(currentGame);
            
        storage.saveCurrentGame(currentGame);
            
            
            return currentGame;
            
        } catch (NotFoundException e) {
            throw new IOException("Error retrieving current game for undo: " + e.getMessage());
        }
    }
    
  
}
