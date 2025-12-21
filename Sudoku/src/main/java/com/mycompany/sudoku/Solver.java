package com.mycompany.sudoku;

public class Solver {
    private final Game game;
    
    public Solver(Game game) {
        this.game = game;
        
        if (game.getEmptyCells() != 5) {
            throw new IllegalArgumentException(
                "Solver only works with exactly 5 empty cells. Found: " + game.getEmptyCells());
        }
    }
    
    public int[] solve() throws NoSolutionException {
        int[][] emptyPositions = game.getEmptyCellPositions();
        
        // Double-check (safety)
        if (emptyPositions.length != 5) {
            throw new IllegalStateException(
                "Game reports " + game.getEmptyCells() + 
                " empty cells but positions array has " + emptyPositions.length);
        }
        
        PermutationIterator iterator = new PermutationIterator(5);
        
        BoardFlyweight flyweight = new BoardFlyweight(game.getBoardCopy());
        
        long combinationsTested = 0;
        while (iterator.hasNext()) {
            int[] combination = iterator.next();
            combinationsTested++;
            
            if (flyweight.isValidWithCombination(emptyPositions, combination)) {
                System.out.println("✓ Solution found after " + combinationsTested + " combinations");
                return createSolutionArray(emptyPositions, combination);
            }
        }
        
        throw new NoSolutionException(
            "No solution found after testing " + combinationsTested + " combinations");
    }
    
    private int[] createSolutionArray(int[][] positions, int[] values) {
        int[] solution = new int[15]; // 5 cells × 3 (row, col, value)
        for (int i = 0; i < 5; i++) {
            solution[i * 3] = positions[i][0];     // row
            solution[i * 3 + 1] = positions[i][1]; // column  
            solution[i * 3 + 2] = values[i];       // value
        }
        return solution;
    }
}