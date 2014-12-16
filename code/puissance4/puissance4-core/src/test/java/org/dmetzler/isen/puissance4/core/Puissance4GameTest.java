package org.dmetzler.isen.puissance4.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.dmetzler.isen.puissance4.core.ChipColour.RED;
import static org.dmetzler.isen.puissance4.core.ChipColour.YELLOW;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by dmetzler on 03/09/2014.
 */
public class Puissance4GameTest {

    private Puissance4Game game;

    @Before
    public void doBefore() throws Exception {
        game = new Puissance4GameImpl();
    }

    @Test
    public void aPlayerMayPlayAColumn() throws Exception {
        game.play(RED, 3);
        assertThat(game.getCell(3, 0)).isEqualTo(RED);

        game.play(RED, 3);
        assertThat(game.getCell(3, 1)).isEqualTo(RED);

        assertThat(game.getCell(3, 2)).isNull();
        assertThat(game.getCell(4, 5)).isNull();
    }

    @Test
    public void itCantPlayOutsideOfTheBoard() throws Exception {
        try {
            game.play(RED, 10);
            fail("It should not be possible to play outside of the board");
        } catch (GameException e) {

        }

        try {
            for (int i = 0; i <= game.getRowsNumber(); i++) {
                game.play(RED, 3);
            }
            fail("It should not be possible to play outside of the board");
        } catch (GameException e) {

        }

    }

    @Test
    public void itCanWinTheGameHorizontally() throws Exception {
        assertThat(game.getWinner()).isNull();
        for (int i = 1; i < 5; i++) {
            game.play(RED, i);
        }
        assertThat(game.getWinner()).isEqualTo(RED);
    }

    @Test
    public void itCanWinTheGameVertically() throws Exception {
        assertThat(game.getWinner()).isNull();
        for (int i = 0; i < 4; i++) {
            game.play(RED, 0);
        }
        System.out.println(game);
        assertThat(game.getWinner()).isEqualTo(RED);

    }

    @Test
    public void itCanWinTheGameDiagonally() throws Exception {
        assertThat(game.getWinner()).isNull();
        game.play(RED, 0);
        game.play(YELLOW, 1);
        game.play(RED, 1);
        game.play(YELLOW, 2);
        game.play(RED, 2);
        game.play(YELLOW, 3);
        game.play(RED, 2);
        game.play(YELLOW, 3);
        game.play(RED, 3);
        game.play(YELLOW, 4);
        game.play(RED, 3);

        assertThat(game.getWinner()).isEqualTo(RED);
        System.out.println(game);

    }
}
