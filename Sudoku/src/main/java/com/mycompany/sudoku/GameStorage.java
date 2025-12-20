package com.mycompany.sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GameStorage {
    private final Path baseDir;
    private final FileManager fileManager;
    private Path FullCurrentGamePath;
    private Path CurrentGameName;

    public GameStorage(String baseDir) {
        this.baseDir = Paths.get(baseDir);
        this.fileManager = new FileManager();
        this.CurrentGameName = null;
        this.FullCurrentGamePath = null;
    }

    // for diff dificulties 
    public void saveGame (Game game , DifficultyEnum difficulty)
    {
        String filename = difficulty.toString().toLowerCase() + "_" + new Random().nextInt(1000) + ".txt";
        Path filePath = baseDir.resolve(difficulty.toString().toLowerCase()).resolve(filename);
        fileManager.writeBoard(filePath, game.getBoard());
    }

    public Game loadRandomGame (DifficultyEnum difficulty) throws NotFoundException
    {
        Path dirPath = baseDir.resolve(difficulty.toString().toLowerCase());
        try{
            List<Path> files = fileManager.listFiles(dirPath);
            if ( files.isEmpty()) {
                throw new NotFoundException("No Games Found For difficulty " + difficulty);
            }
            Path randomFile = files.get( new Random().nextInt(files.size()));
            CurrentGameName = randomFile.getFileName();
            FullCurrentGamePath = randomFile;
            int [][] board = fileManager.readBoard(randomFile);
            return new Game(board);
        }
        catch (Exception e) {
            throw new NotFoundException("Failed to Load Game For difficulty " + difficulty);
        }   
    }

    public void deleteGame()
    {
        if ( FullCurrentGamePath != null) {
            try {
                Files.delete(FullCurrentGamePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FullCurrentGamePath = null;
            CurrentGameName = null;
        }
        
    }

    // for current game
    public void saveCurrentGame (Game game ) throws IOException
    {
        Path gameFile = baseDir.resolve("incomplete").resolve(CurrentGameName);
        Path logFile = baseDir.resolve("incomplete").resolve("log.txt");
        fileManager.writeBoard( gameFile, game.getBoard());
        if (!Files.exists(logFile)) {
            Files.write(logFile, new byte[0]);

    }
}

    public Game loadCurrentGame () throws NotFoundException
    {
        if ( FullCurrentGamePath == null) {
            throw new NotFoundException("No Incomplete Game Found ");
        }
        try{
            int [][] board = fileManager.readBoard(FullCurrentGamePath);
            return new Game(board);
        }
        catch (Exception e) {
            throw new NotFoundException("Failed to Load Incomplete Game " + CurrentGameName);
        }   
    }

    public void deleteCurrentGame()
    {
        Path gameFile = baseDir.resolve("incomplete").resolve(CurrentGameName);
        try {
            Files.delete(gameFile);
        } catch (IOException e) {
            e.printStackTrace();
        FullCurrentGamePath = null;
        CurrentGameName = null;
    }
}
}
