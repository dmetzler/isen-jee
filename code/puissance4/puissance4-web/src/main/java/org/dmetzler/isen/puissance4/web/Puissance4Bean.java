package org.dmetzler.isen.puissance4.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.Puissance4Game;
import org.dmetzler.isen.puissance4.core.Puissance4GameImpl;

@Named("game")
@SessionScoped
public class Puissance4Bean implements Serializable {

    Puissance4Game game = new Puissance4GameImpl();

    ChipColour currentTurn = ChipColour.RED;

    public List<Puissance4Column> getColumns() {

        List<Puissance4Column> cols = new ArrayList<>();
        for (int i = 0; i < game.getColumnsNumber(); i++) {
            cols.add(new Puissance4Column(i, game));
        }
        return cols;

    }

    public void play(int col) {
        game.play(currentTurn, col);
        switchTurn();

    }

    private void switchTurn() {
        currentTurn = currentTurn == ChipColour.RED ? ChipColour.YELLOW
                : ChipColour.RED;
    }

    public void reset() {
        game = new Puissance4GameImpl();

    }

    public ChipColourWrapper getWinner() {
        if (game.getWinner() != null) {
            return new ChipColourWrapper(game.getWinner());
        } else {
            return null;
        }
    }

    public Puissance4Game getGame() {
        return game;
    }

}
