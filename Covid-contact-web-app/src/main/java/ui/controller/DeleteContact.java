package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String contactDate = request.getParameter("contactdate");
        String contactHour = request.getParameter("contacthour");
        service.deleteContact(firstName, lastName, contactDate, contactHour);
        request.setAttribute("contacts", service.getContacts());
        return "contacts.jsp";
    }
}
