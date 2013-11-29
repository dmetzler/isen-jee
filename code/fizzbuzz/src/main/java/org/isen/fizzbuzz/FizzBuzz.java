package org.isen.fizzbuzz;

import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class FizzBuzz {

    @Inject
    Function<Integer, String> transformer;

    @Inject
    @Named("fizzbuzz.range")
    Integer range;

    public void run() {
        Iterator<String> fizzBuzzIt = new Range(range).map(transformer);

        while (fizzBuzzIt.hasNext()) {
            System.out.println(fizzBuzzIt.next());
        }
    }

    public static void main(String args[]) {

        Injector injector = Guice.createInjector(new FizzBuzzModule());
        FizzBuzz fb = injector.getInstance(FizzBuzz.class);

        fb.run();
    }
}
