package com.mycompany.sudoku;

import java.io.IOException;

public interface Viewable {
    Catalog getCatalog();
    Game getGame(DifficultyEnum level) throws NotFoundException;
    void driveGames(Game sourceGame) throws SolutionInvalidException, IOException;
String verifyGame(Game game);
int[] solveGame(Game game) throws InvalidGameException;
void logUserAction(String userAction) throws IOException;
Game undoLastAction() throws IOException;  // Add this
 void abandonCurrentGame() throws IOException , NotFoundException;
// other methods can be added here as needed
}
