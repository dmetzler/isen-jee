package org.isen.guess;

import javax.servlet.http.HttpServletRequest;

public class GuessNumberHelper {

    private static final int MAX_NUMBER = 50;
    private static final int INITIAL_TURNS_LEFT = 10;

    public static GuessGame getGame(HttpServletRequest req) {
        GuessGame game = (GuessGame) req.getSession().getAttribute("game");
        if (game == null) {
            game = new GuessGame(MAX_NUMBER, INITIAL_TURNS_LEFT);
        }

        Integer guessNumber = getGuessNumber(req);
        if (guessNumber != null) {
            game.guess(guessNumber);
        }

        req.getSession().setAttribute("game", game);
        return game;
    }


    private static Integer getGuessNumber(HttpServletRequest req) {
        String strNumber = req.getParameter("number");
        if (strNumber != null) {
            return Integer.parseInt(strNumber);
        }
        return null;
    }

    public static void resetGame(HttpServletRequest request) {
        request.getSession().setAttribute("game",
                new GuessGame(MAX_NUMBER, INITIAL_TURNS_LEFT));

    }

}
