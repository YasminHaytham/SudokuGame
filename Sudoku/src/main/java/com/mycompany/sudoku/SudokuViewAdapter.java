package com.mycompany.sudoku;

import java.io.IOException;
import java.nio.file.Path;

public class SudokuViewAdapter implements Controllable{
    private final Viewable controller;
    
    public SudokuViewAdapter(Viewable controller) {
        this.controller = controller;
    }

    @Override
    public void startNewGame() throws IOException {
        controller.abandonCurrentGame();
    }

    @Override
    public boolean[] getCatalog() {
        Catalog catalog = controller.getCatalog();
        return new boolean[]{catalog.checkGames()[0], catalog.checkGames()[1]};
    }

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        DifficultyEnum difficulty;
        switch (level) {
            case 'E':
                difficulty = DifficultyEnum.EASY;
                break;
            case 'M':
                difficulty = DifficultyEnum.MEDIUM;
                break;
            case 'H':
                difficulty = DifficultyEnum.HARD;
                break;
            case 'I':
                difficulty = null; 
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
        Game game = controller.getGame(difficulty);
        return game.getBoard();
    }

    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException, IOException {
        Game Sourcegame = new GameStorage().loadSolvedBoard(Path.of(sourcePath));

        controller.driveGames(Sourcegame);
    }

    @Override
     public boolean[][] verifyGame(int[][] game) {

        Game g = new Game(game);
        String result = controller.verifyGame(g);

        boolean[][] validity = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                validity[i][j] = true;
            }
        }

        if (result.startsWith("invalid")) {
            String[] parts = result.substring(7).trim().split(" ");
            for (String p : parts) {
                String[] xy = p.split(",");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                validity[x][y] = false;
            }
        }

        return validity;
    }

    @Override
    public int[][] solveGame(int[][] game) throws InvalidGameException {
           Game g = new Game(game);
        int[] solution = controller.solveGame(g);

        int[][] result = new int[solution.length / 3][3];

        for (int i = 0; i < result.length; i++) {
            result[i][0] = solution[i * 3];
            result[i][1] = solution[i * 3 + 1];
            result[i][2] = solution[i * 3 + 2];
        }

        return result;
    }



    @Override
    public void logUserAction(UserAction userAction) throws IOException {
        controller.logUserAction(userAction.toString());
    }
}
