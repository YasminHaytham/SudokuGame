package com.mycompany.sudoku;

import java.nio.file.Path;
import java.util.List;

public class Catalogue {
    private Path baseDir;
    private boolean unfinishedGame;
    private boolean easyMediumHard;

    public Catalogue() {
        this.baseDir = Path.of("games");
        this.unfinishedGame = false;
        this.easyMediumHard = false;
    }

    public void setPath (String newBaseDir) {
        this.baseDir = Path.of(newBaseDir);
    }

    public boolean[] checkGames(){
        FileManager storageFileManager = new FileManager();
        List<Path> unfinishedGames = storageFileManager.listFiles(baseDir.resolve("incomplete"));
       List<Path> easyGames = storageFileManager.listFiles(baseDir.resolve("easy"));
         List<Path> mediumGames = storageFileManager.listFiles(baseDir.resolve("medium"));
          List<Path> hardGames = storageFileManager.listFiles(baseDir.resolve("hard"));
        if (unfinishedGames != null && !unfinishedGames.isEmpty()) {
            this.unfinishedGame = true;
        } else {
            this.unfinishedGame = false;
        }
        if ((easyGames != null && !easyGames.isEmpty()) &&
            (mediumGames != null && !mediumGames.isEmpty()) &&
            (hardGames != null && !hardGames.isEmpty())) {
            this.easyMediumHard = true;
        } else {
            this.easyMediumHard = false;
        }
        return new boolean[]{unfinishedGame, easyMediumHard};
    }
   






}
