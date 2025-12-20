package com.mycompany.sudoku;

import java.util.List;
import java.util.Map;

public class GameGenerator {
    private RandomPairs randomPairs;
    private EnhancedValidator validator;
    private static final int EASY_CELLS_Remove = 10;
    private static final int MEDIUM_CELLS_Remove = 20;
    private static final int HARD_CELLS_Remove = 25;

    public GameGenerator() {
        this.randomPairs = new RandomPairs();
        this.validator = new EnhancedValidator();
    }

    public Map<DifficultyEnum, Game> generateAll(Game SourceGame) throws  SolutionInvalidException
    {
        GameState state = validator.validate(SourceGame);
        if ( state != GameState.VALID) {
            throw new SolutionInvalidException("Source Solution is  " + state);
        }
        else 
        {
            Map<DifficultyEnum, Game> games = new java.util.HashMap<>();
            games.put(DifficultyEnum.EASY, removeCells(SourceGame, EASY_CELLS_Remove));
            games.put(DifficultyEnum.MEDIUM, removeCells(SourceGame, MEDIUM_CELLS_Remove));
            games.put(DifficultyEnum.HARD, removeCells(SourceGame, HARD_CELLS_Remove));
            return games;
        
        }

    }

    public Game removeCells (Game SourceGame , int cells)
    {
        int [][] originalBoard = SourceGame.getBoard();
        int [][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = originalBoard[i][j];
            }
        }
        List <int []> pairs = randomPairs.generateDistinctPairs(cells);
        for (int [] pair : pairs) {
            int x = pair[0];
            int y = pair[1];
            copy[x][y] = 0;
        }
        return new Game (copy);
    }
}
