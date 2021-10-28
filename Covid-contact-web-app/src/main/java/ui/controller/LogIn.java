package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Locale;

public class LogIn extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        // TODO: UPDATE CODE
        /*
        Person person = getModel().getPersonIfAuthenticated(request.getParameter("userid").toLowerCase(Locale.ROOT),
                request.getParameter("password"));

        if(person == null){
            request.setAttribute("error", "User id or password is incorrect.");
        } else {
            request.getSession().setAttribute("person", person);
        }*/
        return "index.jsp";
    }
}
