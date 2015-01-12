package org.dmetzler.isen.puissance4.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang.RandomStringUtils;

public class Puissance4DAO {

    @Inject
    EntityManager em;

    public JPAPuissance4Game createNewGame() {
        JPAPuissance4Game game = new JPAPuissance4Game();
        game.setToken(RandomStringUtils.randomAlphanumeric(10).toLowerCase());
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();

        return game;
    }

    public JPAPuissance4Game loadFromToken(String token) {
        return (JPAPuissance4Game) em.createQuery("SELECT g FROM Game g WHERE g.token = :token")
                .setParameter("token", token).getSingleResult();
    }

}
