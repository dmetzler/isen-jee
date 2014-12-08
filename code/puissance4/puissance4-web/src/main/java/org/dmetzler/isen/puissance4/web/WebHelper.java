package org.dmetzler.isen.puissance4.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.dmetzler.isen.puissance4.core.Puissance4Game;
import org.dmetzler.isen.puissance4.core.Puissance4GameImpl;

public class WebHelper {


	public static Puissance4Game getGame(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Puissance4Game game = (Puissance4Game) session.getAttribute("game");

		if(game == null) {
			game = new Puissance4GameImpl();
			session.setAttribute("game", game);
		}

		String playCol = request.getParameter("playcol");

		if(playCol!=null) {
			game.play(ChipColour.RED, Integer.parseInt(playCol));
		}


		return game;
	}
}
