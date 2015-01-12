package org.dmetzler.isen.puissance4.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.RandomStringUtils;

public class Puissance4DAO {

    @Inject
    EntityManager em;

    public Puissance4Adapter createNewGame() {

        Game game = new Game();
        game.setToken(RandomStringUtils.randomAlphanumeric(10).toLowerCase());
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();

        return new Puissance4Adapter(this, game);
    }

    public Puissance4Adapter loadFromToken(String token) {
        Game game = (Game) em
                .createQuery("SELECT g FROM Game g WHERE g.token = :token")
                .setParameter("token", token).getSingleResult();

        em.createNativeQuery("").getResultList();
        return new Puissance4Adapter(this, game);
    }

    public void save(Game game) {
        em.getTransaction().begin();
        em.merge(game);
        em.getTransaction().commit();

    }

}
