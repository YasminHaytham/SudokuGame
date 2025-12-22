package com.mycompany.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    int [][] readBoard(Path file) throws IOException
    {
        int[][] Board = new int[9][9];
        BufferedReader reader = new BufferedReader(new FileReader(file.toFile()));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            for (int col = 0; col < values.length; col++) {
                Board[row][col] = Integer.parseInt(values[col].trim());
            }
            row++;
        }
        reader.close();
        return Board;
    }

    void writeBoard(Path file, int[][] board) throws IOException
    {
        List<String> lines = new ArrayList<>();
        for (int r = 0; r < 9; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < 9; c++) {
                line.append(board[r][c]);
                if (c < 8) {
                    line.append(",");
                }
            }
            lines.add(line.toString());
        }
            Files.write(file, lines);
        
    }

  public List<Path> listFiles(Path folder) {
        List<Path> fileList = new ArrayList<>();
        
        try {
            // Check if folder exists
            if (!Files.exists(folder)) {
                return fileList;  // Return empty list, not null
            }
            
            // List files with try-with-resources
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
                for (Path entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        fileList.add(entry);
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error listing files in " + folder + ": " + e.getMessage());
            // Return empty list instead of null
        }
        
        return fileList;  // Never returns null
    }

    void deleteFile(Path file) throws IOException
    {
         if (Files.exists(file)) {
            Files.delete(file);
        }
    }

}
