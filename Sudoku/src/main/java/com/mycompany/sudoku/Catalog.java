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

    public boolean[] checkGames(){
        FileManager storageFileManager = new FileManager();
        List<Path> unfinishedGames = storageFileManager.listFiles(baseDir.resolve("incomplete"));
       List<Path> easyGames = storageFileManager.listFiles(baseDir.resolve("easy"));
         List<Path> mediumGames = storageFileManager.listFiles(baseDir.resolve("medium"));
          List<Path> hardGames = storageFileManager.listFiles(baseDir.resolve("hard"));
        if (unfinishedGames != null && !unfinishedGames.isEmpty()) {
            this.hasunfinishedGame = true;
        } else {
            this.hasunfinishedGame = false;
        }
        if ((easyGames != null && !easyGames.isEmpty()) &&
            (mediumGames != null && !mediumGames.isEmpty()) &&
            (hardGames != null && !hardGames.isEmpty())) {
            this.hasEasyMediumHard = true;
        } else {
            this.hasEasyMediumHard = false;
        }
        return new boolean[]{hasunfinishedGame, hasEasyMediumHard};
    }
   


}
