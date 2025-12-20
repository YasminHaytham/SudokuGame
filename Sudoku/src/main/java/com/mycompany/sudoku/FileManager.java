package com.mycompany.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    void writeBoard(Path file, int[][] board)
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
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Path> listFiles(Path folder)
    {
        List<Path> fileList = new ArrayList<>();
        try {
            Files.list(folder).forEach(fileList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    void delete(Path file) throws IOException
    {
         if (Files.exists(file)) {
            Files.delete(file);
        }
    }

}
