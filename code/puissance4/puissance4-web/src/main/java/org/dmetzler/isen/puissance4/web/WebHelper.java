package org.dmetzler.isen.puissance4.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.Puissance4Game;
import org.dmetzler.isen.puissance4.core.Puissance4GameImpl;

public class WebHelper {


    private static final String CURRENTTURN_SESSION_KEY = "currentTurn";
    private static final String GAME_SESSION_KEY = "game";

    public static Puissance4Game getGame(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Puissance4Game game = (Puissance4Game) session.getAttribute(GAME_SESSION_KEY);


        if(game == null) {
                game = new Puissance4GameImpl();
                session.setAttribute(GAME_SESSION_KEY, game);
        }


        String playCol = request.getParameter("playcol");

        if(playCol!=null) {

                ChipColour currentTurn = (ChipColour) session.getAttribute(CURRENTTURN_SESSION_KEY);
                if(currentTurn == null) {
                        currentTurn = ChipColour.RED;
                } else {
                        currentTurn = currentTurn == ChipColour.RED ? ChipColour.YELLOW : ChipColour.RED;
                }
                session.setAttribute(CURRENTTURN_SESSION_KEY, currentTurn);

                game.play(currentTurn, Integer.parseInt(playCol));
        }


        return game;
}
}
