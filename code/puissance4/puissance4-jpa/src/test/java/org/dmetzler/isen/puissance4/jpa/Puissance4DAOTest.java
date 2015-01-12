package org.dmetzler.isen.puissance4.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.jpa.guice.GuiceRunner;
import org.dmetzler.isen.puissance4.jpa.guice.H2DBModule;
import org.dmetzler.isen.puissance4.jpa.guice.Modules;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GuiceRunner.class)
@Modules({H2DBModule.class})
public class Puissance4DAOTest {

    @Inject
    EntityManager em;

    @Inject
    Puissance4DAO dao;


    @Test
    public void daoIsInjected() throws Exception {
        assertThat(dao).isNotNull();
    }

    @Test
    public void itCanCreateAGame() throws Exception {
        Puissance4Adapter game = dao.createNewGame();
        assertThat(game).isNotNull();

        String token = game.getToken();
        assertThat(game.getToken()).isNotNull();
        em.clear();
        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();

    }


    @Test
    public void itCanPlayWithAJPAGame() throws Exception {
        Puissance4Adapter game = dao.createNewGame();
        game.play(ChipColour.RED, 3);
        game.play(ChipColour.RED, 3);
        game.play(ChipColour.YELLOW, 3);
        game.play(ChipColour.YELLOW, 3);
        game.play(ChipColour.RED, 3);

        assertThat(game.getCell(3, 0)).isEqualTo(ChipColour.RED);
        assertThat(game.getCell(3, 1)).isEqualTo(ChipColour.RED);
        assertThat(game.getCell(3, 2)).isEqualTo(ChipColour.YELLOW);
        assertThat(game.getCell(3, 3)).isEqualTo(ChipColour.YELLOW);
        assertThat(game.getCell(3, 4)).isEqualTo(ChipColour.RED);
        String token = game.getToken();


        em.clear();
        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();
        assertThat(game.getCell(3, 0)).isEqualTo(ChipColour.RED);
        assertThat(game.getCell(3, 1)).isEqualTo(ChipColour.RED);
        assertThat(game.getCell(3, 2)).isEqualTo(ChipColour.YELLOW);
        assertThat(game.getCell(3, 3)).isEqualTo(ChipColour.YELLOW);
        assertThat(game.getCell(3, 4)).isEqualTo(ChipColour.RED);


    }
}
