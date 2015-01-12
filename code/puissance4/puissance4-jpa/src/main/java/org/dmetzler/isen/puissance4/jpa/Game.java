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


@Entity(name="Game")
public class Game {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    String token;

    @OneToMany(mappedBy="game", cascade=CascadeType.ALL)
    @OrderColumn(name="index")
    List<Turn> turns = new ArrayList<>();


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

}
