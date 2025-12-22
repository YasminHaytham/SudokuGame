package com.mycompany.sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PermutationIterator implements Iterator<int[]> {
    private final int length;          
    private final int[] current;       
    private final int maxValue = 9;    
    private boolean hasNext = true;
    
    public PermutationIterator(int length) {
        if (length != 5) {
            throw new IllegalArgumentException("Iterator designed for exactly 5 cells");
        }
        this.length = length;
        this.current = new int[length];
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
        
       
        int[] result = current.clone();
        
       
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
        
        
        if (index < 0) {
            hasNext = false;
        }
        
        return result;
    }
    
    
    public long totalPermutations() {
        return (long) Math.pow(maxValue, length);
    }
}
