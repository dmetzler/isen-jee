package org.isen.fizzbuzz;

public class Range extends Mappable<Integer> {

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
        return ++current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }



};
