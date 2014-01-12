package org.isen.guess;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GuessGameTest {


    @Test
    public void anInitialGameHasTriesLeft() throws Exception {
        GuessGame game = new GuessGame(50,10);
        assertThat(game.getTurnLeft()).isEqualTo(10);
    }

    @Test
    public void guessingANumberMakeTurnsLeftDecrease() throws Exception {
        GuessGame game = new GuessGame(50,10);
        game.guess(-1);
        assertThat(game.getTurnLeft()).isEqualTo(9);
    }

    @Test
    public void aGameFailsAfterAllTries() throws Exception {

        GuessGame game = new GuessGame(50, 10);
        for(int i = 0; i< 10 ;i ++) {
            game.guess(-1);
        }

        assertThat(game.hasLost()).isTrue();
    }


    @Test
    public void gamesTellsIfANumberIsTooLow() throws Exception {
        GuessGame game = new GuessGame(50,10);
        game.setNumber(20);
        game.guess(10);
        assertThat(game.tooLow()).isTrue();
        assertThat(game.tooHigh()).isFalse();
        assertThat(game.hasWon()).isFalse();
    }

    @Test
    public void gamesTellsIfANumberIsTooHigh() throws Exception {
        GuessGame game = new GuessGame(50,10);
        game.setNumber(20);
        game.guess(30);
        assertThat(game.tooLow()).isFalse();
        assertThat(game.tooHigh()).isTrue();
        assertThat(game.hasWon()).isFalse();
    }

    @Test
    public void gamesTellsIfIWon() throws Exception {
        GuessGame game = new GuessGame(50,10);
        game.setNumber(20);
        game.guess(20);
        assertThat(game.tooLow()).isFalse();
        assertThat(game.tooHigh()).isFalse();
        assertThat(game.hasWon()).isTrue();
    }
}
