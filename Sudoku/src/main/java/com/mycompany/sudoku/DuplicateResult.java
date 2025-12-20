package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateResult {
    private boolean hasDuplicates = false;
    private Map<Integer, List<Integer>> duplicates = new HashMap<>();

    public boolean isHasDuplicates() {
        return hasDuplicates;
    }

    public void setHasDuplicates(boolean hasDuplicates) {
        this.hasDuplicates = hasDuplicates;
    }

    public Map<Integer, List<Integer>> getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(Map<Integer, List<Integer>> duplicates) {
        this.duplicates = duplicates;
    }

    public void addDuplicate(int value, int position) {
        duplicates.putIfAbsent(value, new ArrayList<>());
        duplicates.get(value).add(position);
        hasDuplicates = true;

    }
}
