package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mode27 implements Validator {

    private final Board board;
    List<String> errors;

    public Mode27(Board board) {
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
        return List.copyOf(errors);
    }

    public void validate() {
        List<String> allErrors = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            final int index = i;
            Thread rowThread = new Thread(() -> {
                SequentialValidator validator = new SequentialValidator(board);
                List<String> rowErrors = validator.ValidateSingleRow(index);
                synchronized (allErrors) {
                    allErrors.addAll(rowErrors);
                }
            });
            threads.add(rowThread);
        }
        for (int i = 0; i < 9; i++) {
            final int index = i;
            Thread columnThread = new Thread(() -> {
                SequentialValidator validator = new SequentialValidator(board);
                List<String> columnErrors = validator.ValidateSingleColumn(index);
                synchronized (allErrors) {
                    allErrors.addAll(columnErrors);
                }
            });
            threads.add(columnThread);
        }
        for (int i = 0; i < 9; i++) {
            final int index = i;
            Thread boxThread = new Thread(() -> {
                SequentialValidator validator = new SequentialValidator(board);
                List<String> boxErrors = validator.ValidateSingleBox(index);
                synchronized (allErrors) {
                    allErrors.addAll(boxErrors);
                }
            });
            threads.add(boxThread);
        }
        for ( Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                allErrors.add("Validation was interrupted");
            }
        }
        errors = allErrors;
    }
}
