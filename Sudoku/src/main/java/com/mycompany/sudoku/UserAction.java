package com.mycompany.sudoku;

public class UserAction {
    private final int x;
    private final int y;
    private final int value;
    private final int previousValue;

    public UserAction(int x, int y, int value, int previousValue) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new IllegalArgumentException(
                    "Coordinates (" + x + "," + y + ") must be 0-8");
        }

        if (value < 0 || value > 9 || previousValue < 0 || previousValue > 9) {
            throw new IllegalArgumentException(
                    "Values must be 0-9. Got: value=" + value + ", prev=" + previousValue);
        }

        this.x = x;
        this.y = y;
        this.value = value;
        this.previousValue = previousValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d,%d)", x, y, value, previousValue);
    }

    public static UserAction fromString(String str) {
        String clean = str.replace("(", "").replace(")", "");
        String[] parts = clean.split(",");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid log format: " + str);
        }

        return new UserAction(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]));
    }
}
