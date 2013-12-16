package org.dmetzler.isen.jpa;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.dmetzler.isen.jpa.guice.GuiceRunner;
import org.dmetzler.isen.jpa.guice.H2DBModule;
import org.dmetzler.isen.jpa.guice.Modules;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;


@RunWith(GuiceRunner.class)
@Modules(H2DBModule.class)
public class EmInjectTest {


    @Inject
    EntityManager em;


    @Test
    public void emIsNotNull() throws Exception {
        assertNotNull(em);
    }


}
