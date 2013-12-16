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
    @Provides //<> Cette annotation spécifie que l'on peut fournir des ##Function<String,String>##
    @Singleton  // <> On peut spécifier que la classe injectée sera un singleton
    public Function<Integer, String> getTransformer(
            @Named("fizzbuzz.transformer.class") String className) { // <> Au sein même du provider on peut demander l'injection de propriétés
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
            //Ici on bind les propriétés à leur nom. On va donc
            //pouvoir injecter les propriétés du fichier fizzbuzz.properties
        	//directement dans nos classes.
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
