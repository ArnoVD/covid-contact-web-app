package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate sessesion -> user not recognized
        request.getSession().invalidate();
        return "index.jsp";
    }
}
