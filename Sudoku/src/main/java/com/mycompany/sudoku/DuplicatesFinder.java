package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public DuplicateResult findDuplicates(int[] values) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        DuplicateResult result = new DuplicateResult();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            map.putIfAbsent(value, new ArrayList<>());

            map.get(value).add(i+1);

        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int value = entry.getKey();
            List<Integer> positions = entry.getValue();
            if (positions.size() > 1) {
                result.setHasDuplicates(true);
                for (int pos : positions) {
                    result.addDuplicate(value, pos);
                }
            }
        }
        return result;
    }
}
