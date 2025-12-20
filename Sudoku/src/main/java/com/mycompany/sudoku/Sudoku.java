/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sudoku;

import java.io.IOException;

/**
 *
 * @author Farah
 */
public class Sudoku {

    public static void main(String[] args) {

    /* 
        if (args.length != 2) {
            System.out.println(
                    "Please provide the input file path and validation mode (0 :sequential, 3: 3 threads, 27: 27 threads).");
            System.exit(1);
        }
        String filePath = args[0];
        int mode = 0;
        try {
            mode = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid mode. Please provide a valid integer (0, 3, or 27).");
            System.exit(1);
        }
        if (mode != 0 && mode != 3 && mode != 27) {
            System.out.println("Invalid mode. Please provide 0 for sequential, 3 for 3 threads, or 27 for 27 threads.");
            System.exit(1);
        }
        try {
            Board board = new Board(filePath);
            Validator validator = ValidatorFactory.getValidator(mode, board);
            boolean isValid = validator.isValid();
            if (isValid) {
                System.out.println("Valid.");
            } else {
                System.out.println("Invalid. Errors:");
                for (String error : validator.getValidationErrors()) {
                    System.out.println(error);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the Sudoku board from file: " + e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }  
     */

        int[][] board = {
          {5,3,4,6,7,8,9,1,2},
{6,7,2,1,9,5,3,4,8},
{1,9,8,3,4,2,5,6,7},
{8,5,9,7,7,1,4,2,3},
{4,2,6,8,5,3,7,9,1},
{7,1,3,9,2,4,8,5,6},
{9,6,1,5,3,7,2,8,4},
{2,8,7,4,1,9,6,3,5},
{3,4,5,2,8,6,1,7,9}
};

Game g = new Game(board);

EnhancedValidator v = new EnhancedValidator();

GameState state = v.validate(g);

System.out.println(state); // INCOMPLETE

}
}
