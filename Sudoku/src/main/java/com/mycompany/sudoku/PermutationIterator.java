package com.mycompany.sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PermutationIterator implements Iterator<int[]> {
    private final int length;          // 5 empty cells
    private final int[] current;       // Current combination
    private final int maxValue = 9;    // Values 1-9
    private boolean hasNext = true;
    
    public PermutationIterator(int length) {
        if (length != 5) {
            throw new IllegalArgumentException("Iterator designed for exactly 5 cells");
        }
        this.length = length;
        this.current = new int[length];
        // Start with all 1's: [1, 1, 1, 1, 1]
        for (int i = 0; i < length; i++) {
            current[i] = 1;
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public int[] next() {
        if (!hasNext) {
            throw new NoSuchElementException("No more permutations");
        }
        
        // Return current combination (copy to avoid modification)
        int[] result = current.clone();
        
        // Generate next combination (like counting in base-9)
        int index = length - 1;
        while (index >= 0) {
            if (current[index] < maxValue) {
                current[index]++;
                break;
            } else {
                current[index] = 1;
                index--;
            }
        }
        
        // If we rolled over from [9,9,9,9,9] to [1,1,1,1,1], we're done
        if (index < 0) {
            hasNext = false;
        }
        
        return result;
    }
    
    // Total number of permutations (9^5 = 59,049)
    public long totalPermutations() {
        return (long) Math.pow(maxValue, length);
    }
}
