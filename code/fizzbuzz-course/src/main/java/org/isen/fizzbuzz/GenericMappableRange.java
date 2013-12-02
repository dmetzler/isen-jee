package org.isen.fizzbuzz;

import java.util.Iterator;

public class GenericMappableRange implements Iterator<Integer> {

    private final int max;
    private int current;

    public GenericMappableRange(int max) {
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

    public <T> Iterator<T> map(final Function<Integer,T> function) {
        final Iterator<Integer> current = this; //<> On sauvegarde l'iterateur courant sous un autre nom
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return current.hasNext(); // <> On se base sur la valeur de la méthode de l'itérateur source
            }

            @Override
            public T next() {
                return function.run(current.next()); // <> On applique la fonction pour toute nouvelle valeur
            }

            @Override
            public void remove() {
                current.remove();
            }

        };
    }

}
