package org.dmetzler.isen.puissance4.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.GameException;
import org.dmetzler.isen.puissance4.core.Puissance4Game;


@Entity(name="Game")
public class JPAPuissance4Game implements Puissance4Game{


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    String token;





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
        return token;
    }

    public void setToken(String token) {
        this.token = token;

    }

}
