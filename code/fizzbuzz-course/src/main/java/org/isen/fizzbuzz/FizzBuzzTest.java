package org.isen.fizzbuzz;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void itCanIterateOverARange() throws Exception {
        Range range = new Range(5); // <> On définit l'objet range
        int i = range.next();
        assertEquals(1, i); // <> La première valeur doit être 1

        while (range.hasNext()) {
            i = range.next();
        }

        assertEquals(5, i); // <> La dernière valeur doit être 5
    }

    @Test
    public void itCanIterateOverARange2() throws Exception {
        Range range = new Range(5); // <> On définit l'objet range
        assertEquals(new Integer(1), range.next()); // <> La première valeur doit être 1
        assertEquals(new Integer(5), goToEnd(range)); // <> La dernière valeur doit être 5
    }




    @Test(expected = RangeOutOfBoundException.class) // <> Cette annotation permet de spécifier que l'on attend une exception de ce type
    public void itSendsAnExceptionWhenCallingNextOnLastValue2() throws Exception {
        Range range = new Range(5);
        goToEnd(range);
        range.next();
    }

    /**
     * go to the end of a range, and returns last value.
     * @param range
     * @return
     */
    public Integer goToEnd(Range range) {
        Integer i = null;
        while (range.hasNext()) {
            i = range.next();
        }
        return i;
    }

    @Test(expected = RangeOutOfBoundException.class) // <> Cette annotation permet de spécifier que l'on attend une exception de ce type
    public void itSendsAnExceptionWhenCallingNextOnLastValue() throws Exception {
        Range range = new Range(5);
        int i = range.next();
        assertEquals(1, i);

        while (range.hasNext()) {
            i = range.next();
        }

        assertEquals(5, i);
        range.next();
    }


    @Test
    public void itCanTransformNumbers() throws Exception {
        FizzBuzzTransformer transformer = new FizzBuzzTransformer();
        assertEquals("1", transformer.transform(1));
        assertEquals("Fizz", transformer.transform(3));
        assertEquals("Buzz", transformer.transform(5));
        assertEquals("FizzBuzz", transformer.transform(15));

    }

    @Test
    public void itCanMapARange() throws Exception {
        MappableRange range = new MappableRange(5); //<> MappableRange est un range contenant la méthode map()
        Iterator<Integer> newRange = range
                .map(new Function<Integer, Integer>() { //<> On définit ici une fonction anonyme

                    @Override
                    public Integer run(Integer i) {
                        return i * 2;  //<> La fonction renvoie le double
                    }
                });

        int i = newRange.next();
        assertEquals(2, i);  // <> La première valeur doit être le double de 1

        while (newRange.hasNext()) {
            i = newRange.next();
        }

        assertEquals(10, i); // <> La dernière valeur doit être le double de 5

    }
}
