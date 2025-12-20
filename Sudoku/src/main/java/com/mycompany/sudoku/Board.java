/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Farah
 */
public class Board {
    private int[][] Board = new int[9][9];
    
    public Board(String cvsFile)throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(cvsFile));
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
    }

    public int[][] getBoard() {
        return Board;
    }

    public int[] getrow(int rowIndex) {
     return Board[rowIndex].clone();
    }

    public int[] getcolumn(int columnIndex) {
        int[] column = new int[9];
        for (int row = 0; row < 9; row++) {
            column[row] = Board[row][columnIndex];
        }
        return column;
    }

    public int[] getbox(int boxIndex) {
    int[] values = new int[9];
    int cornerRow = (boxIndex / 3) * 3;
    int cornerColumn = (boxIndex % 3) * 3;
    int i = 0;
    for(int r = 0 ; r < 3 ; r++){
        for(int c = 0 ; c < 3 ; c++){
            values[i++] = Board[cornerRow + r][cornerColumn + c];
        }
    }
    return values;
}

}
