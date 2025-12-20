package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mode3 implements Validator {

    private final Board board;
    List<String> errors;

    public Mode3(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValid() {
        if (errors == null) {
            validate();
        }
        return errors.isEmpty();
    }

    @Override
    public List<String> getValidationErrors() {
        if (errors == null) {
            validate();
        }
        return new ArrayList<>(errors);
    }

    public void validate() {
        List<String> allErrors = Collections.synchronizedList(new ArrayList<>());

        Thread rowThread = new Thread(() -> {
            SequentialValidator validator = new SequentialValidator(board);
            for (int i = 0; i < 9; i++) {
                List<String> rowErrors = validator.ValidateSingleRow(i);
                synchronized(allErrors) {
                    allErrors.addAll(rowErrors);
                }
            }
        });
        Thread columnThread = new Thread(() -> {
            SequentialValidator validator = new SequentialValidator(board);
            for (int i = 0; i < 9; i++) {
                List<String> columnErrors = validator.ValidateSingleColumn(i);
                synchronized(allErrors) {
                    allErrors.addAll(columnErrors);
                }
            }
        });
        Thread boxThread = new Thread(() -> {
            SequentialValidator validator = new SequentialValidator(board);
            for (int i = 0; i < 9; i++) {
                List<String> boxErrors = validator.ValidateSingleBox(i);
                synchronized(allErrors) {
                    allErrors.addAll(boxErrors);
                }
            }
        });
        rowThread.start();
         try {
            rowThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            allErrors.add("Row Validation was interrupted");
        }
        columnThread.start();
         try {
            columnThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            allErrors.add("Column Validation was interrupted");
        }
        boxThread.start();
        try {
            boxThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            allErrors.add("Box Validation was interrupted");
        }
        this.errors = allErrors;

    }

}
