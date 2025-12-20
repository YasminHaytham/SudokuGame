package com.mycompany.sudoku;

public class DifficultyEnum {

    public static final DifficultyEnum EASY = new DifficultyEnum("EASY");
    public static final DifficultyEnum MEDIUM = new DifficultyEnum("MEDIUM");
    public static final DifficultyEnum HARD = new DifficultyEnum("HARD");

    private final String name;

    private DifficultyEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
