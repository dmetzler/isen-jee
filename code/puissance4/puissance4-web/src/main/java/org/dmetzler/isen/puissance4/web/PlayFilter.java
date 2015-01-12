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
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

@WebFilter(urlPatterns = "/*", filterName = "playFilter")
public class PlayFilter implements Filter {

    @Inject
    Puissance4Bean game;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getRequestURI().contains("css")) {
            chain.doFilter(request, response);
        } else {

            String token = getTokenFromRequest(httpRequest);

            response.getWriter().write("Hello " + token);

            if (StringUtils.isEmpty(token)
                    || request.getParameter("reset") != null) {
                game.createNewGame();
                redirectToGameRoot(response, httpRequest);
            } else {
                game.loadFromToken(token);

                String playCol = request.getParameter("playcol");
                if (playCol != null) {
                    game.play(Integer.parseInt(playCol));
                    redirectToGameRoot(response, httpRequest);
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request,

                    response);
                }

            }
        }

    }

    private void redirectToGameRoot(ServletResponse response,
            HttpServletRequest httpRequest) throws IOException {
        ((HttpServletResponse) response).sendRedirect(httpRequest
                .getContextPath() + "/" + game.getToken());
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        String token = request.getRequestURI().substring(
                request.getContextPath().length() + 1);
        return "index.jsp".equals(token) ? "" : token;

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
