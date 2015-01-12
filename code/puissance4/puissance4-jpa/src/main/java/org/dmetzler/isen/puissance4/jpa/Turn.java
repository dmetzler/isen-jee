package org.dmetzler.isen.puissance4.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.dmetzler.isen.puissance4.core.ChipColour;

@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    Game game;

    private String colour;
    private int column;

    public Turn() {

    }

    public Turn(Game game, ChipColour colour, int column) {
        this.game = game;
        this.colour = colour.toString();
        this.column = column;
    }

    public ChipColour getColour() {
        return ChipColour.valueOf(colour);
    }

    public int getColumn() {
        return column;
    }


}
