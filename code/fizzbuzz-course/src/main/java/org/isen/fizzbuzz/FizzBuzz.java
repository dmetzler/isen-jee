package org.isen.fizzbuzz;

import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class FizzBuzz {

    @Inject // <> L'annotation spécifie que transformer doit être injecté
    Function<Integer, String> transformer;

    @Inject
    @Named("fizzbuzz.range") //<> On peut aussi injecter en spécifiant des noms
    Integer range;

    public void run() {
        Iterator<String> fizzBuzzIt = new MappableRange(range).map(transformer);

        while (fizzBuzzIt.hasNext()) {
            System.out.println(fizzBuzzIt.next());
        }
    }

    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new FizzBuzzModule()); //<> On configure l'injecteur
        FizzBuzz fb = injector.getInstance(FizzBuzz.class); //<> L'injecteur instancie la classe
        fb.run();
    }
}
