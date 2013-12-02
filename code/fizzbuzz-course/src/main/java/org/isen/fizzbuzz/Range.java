package org.isen.fizzbuzz;

import java.util.Iterator;

public class Range implements Iterator<Integer> {

    private final int max;
    private int current;

    public Range(int max) {
        current = 0;
        this.max = max;

    }

    @Override
    public boolean hasNext() {
        return current < max;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return ++current;
        } else {
            throw new RangeOutOfBoundException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }

};
