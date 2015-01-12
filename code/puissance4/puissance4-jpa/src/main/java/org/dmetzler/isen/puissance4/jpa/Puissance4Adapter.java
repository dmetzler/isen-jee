package org.dmetzler.isen.puissance4.jpa;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.GameException;
import org.dmetzler.isen.puissance4.core.Puissance4Game;
import org.dmetzler.isen.puissance4.core.Puissance4GameImpl;

public class Puissance4Adapter implements Puissance4Game {

    private Game game;


    private Puissance4Game coreGame;


    private Puissance4DAO dao;

    public Puissance4Adapter(Puissance4DAO dao, Game game) {
        this.dao = dao;
        this.game = game;
        this.coreGame = new Puissance4GameImpl();

        for(Turn turn : game.getTurns()) {
            this.coreGame.play(turn.getColour(), turn.getColumn());
        }




    }

    @Override
    public void play(ChipColour colour, int column) throws GameException {
        coreGame.play(colour, column);
        this.game.getTurns().add(new Turn(this.game, colour,column));

        dao.save(game);



    }

    @Override
    public ChipColour getCell(int column, int row) {
        return coreGame.getCell(column, row);
    }

    @Override
    public int getColumnsNumber() {
        return coreGame.getColumnsNumber();
    }

    @Override
    public int getRowsNumber() {
        return coreGame.getRowsNumber();
    }

    @Override
    public ChipColour getWinner() {
        return coreGame.getWinner();
    }

    public String getToken() {
        return game.getToken();
    }

}
