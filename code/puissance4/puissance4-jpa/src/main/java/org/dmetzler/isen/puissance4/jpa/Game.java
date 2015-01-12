package org.dmetzler.isen.puissance4.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.dmetzler.isen.puissance4.core.ChipColour;


@Entity(name="Game")
public class Game {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToMany(mappedBy="game", cascade=CascadeType.ALL)
    @OrderColumn(name="index")
    private List<Turn> turns = new ArrayList<>();

    private String currentTurn = ChipColour.RED.toString();

    public Game() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;

    }

    public List<Turn> getTurns() {
        return turns;
    }

    public ChipColour getCurrentTurn() {
        return  ChipColour.valueOf(currentTurn);
    }

    public void setCurrentTurn(ChipColour colour) {
        currentTurn = colour.toString();
    }

}
