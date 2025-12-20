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
    }
}
