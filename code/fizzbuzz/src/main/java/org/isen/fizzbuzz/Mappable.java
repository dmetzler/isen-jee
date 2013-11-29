package org.isen.fizzbuzz;
import java.util.Iterator;

public abstract class Mappable<T> implements Iterator<T> {
    public <S> Iterator<S> map(final Function<T, S> function) {
        final Mappable<T> current = this;
        return new Iterator<S>() {

            @Override
            public boolean hasNext() {
                return current.hasNext();
            }

            @Override
            public S next() {
                return function.run(current.next());
            }

            @Override
            public void remove() {
                current.remove();
            }

        };
    }
}
