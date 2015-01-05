package org.dmetzler.isen.puissance4.jpa;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.GameException;
import org.dmetzler.isen.puissance4.core.Puissance4Game;

public class JPAPuissance4Game implements Puissance4Game{

    @Override
    public void play(ChipColour colour, int column) throws GameException {
        // TODO Auto-generated method stub

    }

    @Override
    public ChipColour getCell(int column, int row) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getColumnsNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRowsNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ChipColour getWinner() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getToken() {
        // TODO Auto-generated method stub
        return null;
    }

}
