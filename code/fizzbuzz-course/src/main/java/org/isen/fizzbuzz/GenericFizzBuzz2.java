package org.isen.fizzbuzz;

import java.util.Iterator;

public class GenericFizzBuzz2 {

    public static void main(String[] args) {
        Iterator<String> fizzBuzzIterator = new MappableRange(199)
                .map(new FizzBuzzTransformer());
        while(fizzBuzzIterator.hasNext()) {
            System.out.println(fizzBuzzIterator.next());
        }
    }

}
