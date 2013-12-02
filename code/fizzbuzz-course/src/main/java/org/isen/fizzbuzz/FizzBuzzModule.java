package org.isen.fizzbuzz;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class FizzBuzzModule extends AbstractModule {

    @SuppressWarnings("unchecked")
    @Provides
    @Singleton
    public Function<Integer, String> getTransformer(
            @Named("fizzbuzz.transformer.class") String className) {
        try {
            Class<?> klass = Class.forName(className);
            return (Function<Integer, String>) klass.newInstance();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException e) {
            return null;
        }

    }

    @Override
    protected void configure() {
        try {
            Names.bindProperties(binder(), getProperties());
        } catch (IOException e) {
            System.out.println("Unable to bind properties");
        }

    }

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        URL url = getClass().getClassLoader()
                .getResource("fizzbuzz.properties");
        props.load(url.openStream());
        return props;
    }

}
