package org.isen.fizzbuzz;

public class GenericFizzBuzz {

    public static void main(String[] args) {
        for (int i = 1; i < 200; i++) { // <1> On itère de 1 à 199

            boolean fizz = i % 3 == 0; // <2> On évalue les conditions de l'itération
            boolean buzz = i % 5 == 0;

            if (fizz && buzz) {	// <3> On affiche ensuite en fonction des cas
                System.out.println("FizzBuzz");
            } else if (fizz) {
                System.out.println("Fizz");
            } else if (buzz) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }

        }
    }
}
