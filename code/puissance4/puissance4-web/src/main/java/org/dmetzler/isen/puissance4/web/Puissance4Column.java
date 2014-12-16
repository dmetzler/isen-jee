package org.dmetzler.isen.puissance4.web;

import java.util.ArrayList;
import java.util.List;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.Puissance4Game;

public class Puissance4Column {

    private int index;
    private Puissance4Game game;

    public Puissance4Column(int i, Puissance4Game game) {
        this.index = i;
        this.game = game;
    }

    public List<ChipColourWrapper> getCells() {
        List<ChipColourWrapper> cells = new ArrayList<>();
        for (int i = game.getRowsNumber() - 1; i >= 0; i--) {
            cells.add(new ChipColourWrapper(game.getCell(index, i)));
        }
        return cells;
    }

    public int getIndex() {
        return index;
    }

}
