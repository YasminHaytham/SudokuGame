package com.mycompany.sudoku;

import java.io.IOException;

public interface Controllable {

boolean[] getCatalog();
int[][] getGame(char level) throws NotFoundException;
void driveGames(String sourcePath) throws SolutionInvalidException , IOException;
// A boolean array which says if a specifc cell is correct or invalid
boolean[][] verifyGame(int[][] game);
// contains the cell x, y and solution for each missing cell
int[][] solveGame(int[][] game) throws InvalidGameException;
// Logs the user action
void logUserAction(UserAction userAction) throws IOException;
}

