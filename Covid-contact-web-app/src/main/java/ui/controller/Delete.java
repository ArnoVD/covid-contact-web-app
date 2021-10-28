package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        // TODO: UPDATE CODE
        /*
        String userid = request.getParameter("userid");
        service.deletePerson(userid);
        request.setAttribute("persons", service.getPersons());*/
        return "overview.jsp";
    }
}
