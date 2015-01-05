package org.dmetzler.isen.puissance4.jpa.guice;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceRunner extends BlockJUnit4ClassRunner {


    private Injector injector;


    public GuiceRunner(final Class<?> classToRun)
            throws org.junit.runners.model.InitializationError {
        super(classToRun);
    }


    @Override
    public Object createTest() {

        return getInjector().getInstance(getTestClass().getJavaClass());
    }

    private Injector getInjector() {
        if(injector == null) {
            List<Module> modules = getModules();
            injector = Guice.createInjector(modules);
        }
        return injector;
    }


    private List<Module> getModules() {
        List<Module> modules = new ArrayList<Module>();
        Description description = getDescription();
        Modules modulesClass = description.getAnnotation(Modules.class);
        for(Class<? extends Module> klass : modulesClass.value()) {
            try {
                modules.add(klass.newInstance());
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return modules;
    }


    @Override
    protected void validateZeroArgConstructor(List<Throwable> errors) {
        // Guice can inject constructors with parameters so we don't want this
        // method to trigger an error
    }



}
