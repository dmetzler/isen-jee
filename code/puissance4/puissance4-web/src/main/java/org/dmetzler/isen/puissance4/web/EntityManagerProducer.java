package org.dmetzler.isen.puissance4.web;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    @PersistenceContext(name="db-manager")
    EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }
}
