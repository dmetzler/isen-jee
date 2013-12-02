package org.isen.fizzbuzz;
public class GenericFizzBuzzTransformer {

    public String transform(Integer i) {
        boolean fizz = i % 3 == 0;
        boolean buzz = i % 5 == 0;

        if (fizz && buzz) {
            return "FizzBuzz";
        } else if (fizz) {
            return "Fizz";
        } else if (buzz) {
            return "Buzz";
        } else {
            return Integer.toString(i);
        }
    }

}
