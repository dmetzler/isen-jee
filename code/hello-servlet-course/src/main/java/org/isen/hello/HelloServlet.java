package org.isen.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String getHtml(String name) {

        return "<html>"//
                + "<body>Servlet version" //
                + "<h1>Hello " + name + "</h1>"//
                + "<form method='GET'>"//
                + "  <input name='name'/><input type='submit'/>"//
                + "</form>"//
                + "</body></html>";//
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("Hello World");
    }


    protected void doGet2(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = getNameFromRequest(req);

        out.print(getHtml(name));
    }

    public static String getNameFromRequest(HttpServletRequest req) {
        String name = req.getParameter("name");
        if (StringUtils.isNotEmpty(name)) {
            req.getSession().setAttribute("name", name);
        } else {
            name = (String) req.getSession().getAttribute("name");
            name = StringUtils.isEmpty(name) ? "World" : name;
        }

        return name;
    }

}
