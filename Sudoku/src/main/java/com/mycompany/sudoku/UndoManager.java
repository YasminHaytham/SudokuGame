package com.mycompany.sudoku;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Stack;

public class UndoManager {
    private final Path logFilePath;
    private final Stack<String> undoStack;
    
    public UndoManager( String gameFolderPath) {
        this.undoStack = new Stack<>();
        
        Path incompletePath = Paths.get(gameFolderPath, "incomplete");
        this.logFilePath = incompletePath.resolve("log.txt");
        
        loadExistingLog();
    }

    
   
    
    private void parseAndApplyAction(String action, boolean undo, Game currentGame) {
        String clean = action.replace("(", "").replace(")", "");
        String[] parts = clean.split(",");
        
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid action format: " + action);
        }
        
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        int newVal = Integer.parseInt(parts[2]);
        int prevVal = Integer.parseInt(parts[3]);
        
        if (undo) {
          
            currentGame.setCell(x, y, prevVal);
        } else {
            currentGame.setCell(x, y, newVal);
        }
    }
    
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }
    
    
    private void writeToLog(String action) {
        try (BufferedWriter writer = Files.newBufferedWriter(logFilePath, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(action);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to log: " + logFilePath, e);
        }
    }
       private void loadExistingLog() {
        if (!Files.exists(logFilePath)) {
            return;
        }
        
        try {
            List<String> lines = Files.readAllLines(logFilePath);
            undoStack.clear(); 
            undoStack.addAll(lines);
        } catch (IOException e) {
            System.err.println("Warning: Could not load undo log: " + e.getMessage());
        }
    }
    
    public void logAction(int x, int y, int newValue, int previousValue) {
        
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        if (newValue < 0 || newValue > 9 || previousValue < 0 || previousValue > 9) {
            throw new IllegalArgumentException("Values must be 0-9");
        }
        
       
        String logEntry = x + "," + y + "," + newValue + "," + previousValue;
        
        undoStack.push(logEntry);
        writeToLog(logEntry);
    }
    
    public void undo(Game game) throws IOException {
        if (!canUndo()) {
            return;
        }
        
        String lastAction = undoStack.pop();
        parseAndApplyAction(lastAction, true, game);
        removeLastLineFromLog();
    }
    
private void removeLastLineFromLog() throws IOException {
    if (!Files.exists(logFilePath)) {
        return;
    }
    
    List<String> lines = Files.readAllLines(logFilePath);
    if (!lines.isEmpty()) {
        lines.remove(lines.size() - 1);
        
        
        Files.write(logFilePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
    } else {
        
        Files.deleteIfExists(logFilePath);
    }
}
}