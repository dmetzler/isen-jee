package org.isen.fizzbuzz;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.isen.fizzbuzz.FizzBuzzTransformer;
import org.isen.fizzbuzz.Function;
import org.isen.fizzbuzz.Range;
import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void itCanIterateOverARange() throws Exception {
        Range range = new Range(5);
        int i = range.next();
        assertEquals(1, i);

        while (range.hasNext()) {
            i = range.next();
        }

        assertEquals(5, i);
    }

    @Test
    public void itCanTransformNumbers() throws Exception {
        FizzBuzzTransformer transformer = new FizzBuzzTransformer();
        assertEquals("1", transformer.run(1));
        assertEquals("Fizz", transformer.run(3));
        assertEquals("Buzz", transformer.run(5));
        assertEquals("FizzBuzz", transformer.run(15));

    }

    @Test
    public void itCanMapARange() throws Exception {
        Range range = new Range(5);
        Iterator<Integer> newRange = range
                .map(new Function<Integer, Integer>() {

                    @Override
                    public Integer run(Integer i) {
                        return i * 2;
                    }
                });

        int i = newRange.next();
        assertEquals(2, i);

        while (newRange.hasNext()) {
            i = newRange.next();
        }

        assertEquals(10, i);

    }
}
