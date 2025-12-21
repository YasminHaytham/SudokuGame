package com.mycompany.sudoku;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoManager {
    private final Path logFilePath;
    private final Stack<UserAction> undoStack;
    
    public UndoManager( String gameFolderPath) {
        this.undoStack = new Stack<>();
        
        Path incompletePath = Paths.get(gameFolderPath, "incomplete");
        
        this.logFilePath = incompletePath.resolve("log.txt");
        
        loadExistingLog();
    }
    public void logAction(int x, int y, int newValue, int previousValue) {
        // Validate inputs
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new IllegalArgumentException(
                "Coordinates (" + x + "," + y + ") must be between 0 and 8");
        }

        if (newValue < 0 || newValue > 9 || previousValue < 0 || previousValue > 9) {
            throw new IllegalArgumentException(
                "Sudoku values must be 0-9. Got: new=" + newValue + ", prev=" + previousValue);
        }
        
        UserAction action = new UserAction(x, y, newValue, previousValue);
        
        undoStack.push(action);
        
        writeToLog(action);
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }
    
    public void undo( Game game) throws IOException {
        if (!canUndo()) {
            throw new IllegalStateException("No actions to undo");
        }
        
        UserAction lastAction = undoStack.pop();
        game.setCell(lastAction.getX(), lastAction.getY(), lastAction.getPreviousValue());
        removeLastLineFromLog();
    }
    public void clearLog() throws IOException {
        undoStack.clear();
        if (Files.exists(logFilePath)) {
            Files.delete(logFilePath);
        }
    }
    public int getUndoCount() {
        return undoStack.size();
    }
    public Path getLogFilePath() {
        return logFilePath;
    }
    private void writeToLog(UserAction action) {
        try (BufferedWriter writer = Files.newBufferedWriter(logFilePath, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(action.toString());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to log file: " + logFilePath, e);
        }
    }
    private void removeLastLineFromLog() throws IOException {
        if (!Files.exists(logFilePath)) {
            return;
        }
        
        List<String> lines = Files.readAllLines(logFilePath);
        if (!lines.isEmpty()) {
            lines.remove(lines.size() - 1);
            
            if (lines.isEmpty()) {
                Files.delete(logFilePath);
            } else {
                Files.write(logFilePath, lines);
            }
        }
    }
    
    private void loadExistingLog() {
        if (!Files.exists(logFilePath)) {
            return;
        }
        
        try {
            if (Files.size(logFilePath) == 0) {
                return;
            }
            
            List<String> lines = Files.readAllLines(logFilePath);
            int loadedCount = 0;
            for (String line : lines) {
                try {
                    UserAction action = UserAction.fromString(line);
                    undoStack.push(action);
                    loadedCount++;
                } catch (IllegalArgumentException e) {
                    System.err.println("Warning: Skipping corrupted log line: " + line);
                }
            }
            
            if (loadedCount > 0) {
                System.out.println("Loaded " + loadedCount + " undo actions from: " + logFilePath);
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load existing log from: " + logFilePath, e);
        }
    }
    public void validateFolderState() throws IOException {
        Path folder = logFilePath.getParent();
        if (Files.exists(folder)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
                List<Path> files = new ArrayList<>();
                for (Path entry : stream) {
                    files.add(entry);
                }
                if (files.size() != 0 && files.size() != 2) {
                    System.err.println("WARNING: Incomplete folder should have 0 or 2 files. Found: " + files.size());
                }
                
                if (files.size() == 2) {
                    // Should contain: one .sdk game file and one log.txt file
                    boolean hasLog = files.stream().anyMatch(p -> 
                        p.getFileName().toString().equals("log.txt"));
                    boolean hasSdk = files.stream().anyMatch(p -> 
                        p.getFileName().toString().toLowerCase().endsWith(".sdk"));
                    
                    if (!hasLog || !hasSdk) {
                        System.err.println("WARNING: Incomplete folder should contain log.txt and a .sdk file");
                    }
                }
            }
        }
    }
}