package org.dmetzler.isen.puissance4.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

@WebFilter(urlPatterns="/*", filterName="playFilter")
public class PlayFilter implements Filter{

    @Inject
    Puissance4Bean game;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {


        String token = getTokenFromRequest((HttpServletRequest) request);

        if(StringUtils.isEmpty(token) || request.getParameter("reset") !=null) {
            game.createNewGame();
            redirect(game.getToken());
        } else {
            game.loadFromToken(token);

            String playCol = request.getParameter("playcol");
            if(playCol!=null) {
                game.play(Integer.parseInt(playCol));
            }

            chain.doFilter(request, response);
        }






    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
