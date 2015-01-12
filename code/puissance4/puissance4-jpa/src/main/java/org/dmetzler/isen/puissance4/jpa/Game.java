package org.dmetzler.isen.puissance4.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="Game")
public class Game {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    String token;

    List<Turn> turns = new ArrayList<>();

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
