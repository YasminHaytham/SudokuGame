package com.mycompany.sudoku;

import java.nio.file.Path;
import java.util.List;

public class Catalog {
    private Path baseDir;
    private boolean hasunfinishedGame;
    private boolean hasEasyMediumHard;

    public Catalog() {
        this.baseDir = Path.of("games");
        this.hasunfinishedGame = false;
        this.hasEasyMediumHard = false;
    }

public boolean[] checkGames() {
        try {
            FileManager storageFileManager = new FileManager();
            
            // Check incomplete folder
            List<Path> unfinishedGames = storageFileManager.listFiles(
                baseDir.resolve("incomplete"));
            this.hasunfinishedGame = (unfinishedGames != null && !unfinishedGames.isEmpty());
            
            // Check difficulty folders
            List<Path> easyGames = storageFileManager.listFiles(baseDir.resolve("easy"));
            List<Path> mediumGames = storageFileManager.listFiles(baseDir.resolve("medium"));
            List<Path> hardGames = storageFileManager.listFiles(baseDir.resolve("hard"));
            
            // IMPORTANT: Check for null AND empty
            boolean easyExists = easyGames != null && !easyGames.isEmpty();
            boolean mediumExists = mediumGames != null && !mediumGames.isEmpty();
            boolean hardExists = hardGames != null && !hardGames.isEmpty();
            
            this.hasEasyMediumHard = easyExists && mediumExists && hardExists;
            
            return new boolean[]{hasunfinishedGame, hasEasyMediumHard};
            
        } catch (Exception e) {
            System.err.println("Error checking catalog: " + e.getMessage());
            return new boolean[]{false, false};  // Return default values
        }
    }
   


}
