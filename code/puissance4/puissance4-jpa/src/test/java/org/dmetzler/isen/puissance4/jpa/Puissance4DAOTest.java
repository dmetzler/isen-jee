package org.dmetzler.isen.puissance4.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.dmetzler.isen.puissance4.core.Puissance4Game;
import org.dmetzler.isen.puissance4.jpa.guice.GuiceRunner;
import org.dmetzler.isen.puissance4.jpa.guice.H2DBModule;
import org.dmetzler.isen.puissance4.jpa.guice.Modules;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GuiceRunner.class)
@Modules({H2DBModule.class})
public class Puissance4DAOTest {


    @Inject
    Puissance4DAO dao;


    @Test
    public void daoIsInjected() throws Exception {
        assertThat(dao).isNotNull();
    }

    @Test
    public void itCanCreateAGame() throws Exception {
        JPAPuissance4Game game = dao.createNewGame();
        assertThat(game).isNotNull();

        String token = game.getToken();

        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();

    }
}
