package org.isen.fizzbuzz;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class FizzBuzzFactory {

    @SuppressWarnings("unchecked")
    public Function<Integer, String> getTransformer() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties props = new Properties();

        URL url = getClass().getClassLoader().getResource("fizzbuzz.properties");
        props.load(url.openStream());

        Class<?> klass = Class.forName(props.getProperty("fizzbuzz.transformer.class"));
        return (Function<Integer, String>) klass.newInstance();


    }

}
