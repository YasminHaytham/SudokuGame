package com.mycompany.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

public class GameStorage {

    private Path baseDir;
    private FileManager fileManager;
    private Path FullCurrentGamePath;
    private Path CurrentGameName;


    public GameStorage() {
        this.baseDir = Paths.get("games");
        this.fileManager = new FileManager();
        verifyDirectory(this.baseDir);

    }

    public void verifyDirectory(Path dirPath) {
        try {
            Files.createDirectories(this.baseDir);
            Files.createDirectories(this.baseDir.resolve("easy"));
            Files.createDirectories(this.baseDir.resolve("medium"));
            Files.createDirectories(this.baseDir.resolve("hard"));
            Files.createDirectories(this.baseDir.resolve("incomplete"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create storage folders", e);
        }
    }

    public Game loadSolvedBoard(Path filePath) throws IOException {
        int[][] board = fileManager.readBoard(filePath);
        return new Game(board);
    }

    // for diff dificulties 
    public boolean saveGame(DifficultyEnum difficulty, Game game) {
        String filename = difficulty.toString().toLowerCase() + "_" + System.currentTimeMillis() + ".sdk";
        Path folder = baseDir.resolve(difficulty.toString().toLowerCase());
        Path filePath = folder.resolve(filename);
        try {
        fileManager.writeBoard(filePath, game.getBoard());
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public Game readGame(DifficultyEnum difficulty) throws NotFoundException {
        Path dirPath = baseDir.resolve(difficulty.toString().toLowerCase());
        List<Path> files = fileManager.listFiles(dirPath);
        if (files.isEmpty() || files == null) {
            throw new NotFoundException("No Games Found For difficulty " + difficulty);
        }
        try {
            Random rand = new Random();
            Path randomFile = files.get(rand.nextInt(files.size()));
            this.CurrentGameName = randomFile.getFileName();
            int[][] board = fileManager.readBoard(randomFile);
            Path incompleteDir = baseDir.resolve("incomplete");
            Path gameFile = incompleteDir.resolve(CurrentGameName);
            Path logFile = incompleteDir.resolve("log.txt");
            Files.copy(randomFile, gameFile, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(logFile);
            Files.createFile(logFile);
            return new Game(board);
        } catch (Exception e) {
            throw new NotFoundException("Failed to Load Game For difficulty " + difficulty);
        }

    }

    public boolean deleteGameFromFolder(DifficultyEnum difficulty) {
        Path dirPath = baseDir.resolve(difficulty.toString().toLowerCase());
        Path filePath = dirPath.resolve(CurrentGameName);
        try {
            fileManager.deleteFile(filePath);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

   
    public boolean saveCurrentGame(Game game) throws IOException {
        Path gameFile = baseDir.resolve("incomplete").resolve(CurrentGameName);
        try{
        fileManager.writeBoard(gameFile, game.getBoard());
        }
        catch (IOException e) {
            return false;
        }
        return true;
       
    }
    public boolean updateGameInFolder(DifficultyEnum difficulty, Game game) {
        Path dirPath = baseDir.resolve(difficulty.toString().toLowerCase());
        Path filePath = dirPath.resolve(CurrentGameName);
        try {
            fileManager.writeBoard(filePath, game.getBoard());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Game ReadCurrentGame() throws NotFoundException {
        Path incompleteDir = baseDir.resolve("incomplete");
        File dir = incompleteDir.toFile();
        File[] files = dir.listFiles();

        File sdkFile = null;
        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName().toLowerCase();
                if (name.endsWith(".sdk")) {
                    sdkFile = file;
                    break;
                }
            }
        }
        Path gameFile = incompleteDir.resolve(sdkFile.getName());
        try {
            FullCurrentGamePath = gameFile;
            this.CurrentGameName = gameFile.getFileName();
            int[][] board = fileManager.readBoard(FullCurrentGamePath);
            return new Game(board);
        } catch (Exception e) {
            throw new NotFoundException("Failed to Load Incomplete Game " + CurrentGameName);
        }
    }

    public boolean deleteCurrentGame() {
        Path gameFile = baseDir.resolve("incomplete").resolve(CurrentGameName);
        Path logFile = baseDir.resolve("incomplete").resolve("log.txt");
        try {
            fileManager.deleteFile(gameFile);
            fileManager.deleteFile(logFile);
            FullCurrentGamePath = null;
            CurrentGameName = null;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

     public DifficultyEnum getCurrentDifficulty() {
        String name = CurrentGameName.toString();
        if (name == null || name.isEmpty()) {
            return null;
        }
        
        String nameWithoutExt = name.replace(".sdk", "").toLowerCase();
        
        String[] parts = nameWithoutExt.split("_");
        
        if (parts.length >= 1) {
            String difficultyStr = parts[0]; 
            
            switch (difficultyStr) {
                case "easy":
                    return DifficultyEnum.EASY;
                case "medium":
                    return DifficultyEnum.MEDIUM;
                case "hard":
                    return DifficultyEnum.HARD;
                default:
                    return null;
            }
        }
        
        return null;
    }


    public Game getCurrentGame() throws NotFoundException {
        return ReadCurrentGame();
    }
      
public Path getCurrentGamePath() {
    return baseDir.resolve("incomplete").resolve(CurrentGameName);
}

public String getCurrentGameFolderPath() {
    return baseDir.toString();
}


}
