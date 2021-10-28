package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Contacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("contacts", service.getContacts());

        // If sign up button is clicked
        if (request.getParameter("addContact") != null)
        {
            ArrayList<String> errors = new ArrayList<>();

            Contact contact = new Contact();
            setContactAttribute(contact, request, errors, "contactFirstName", "contactFirstNameClass", "contactFirstNamePreviousValue");
            setContactAttribute(contact, request, errors, "contactLastName", "contactLastNameClass", "contactLastNamePreviousValue");
            setContactAttribute(contact, request, errors, "contactDate", "contactDateNameClass", "contactDatePreviousValue");
            setContactAttribute(contact, request, errors, "contactHour", "contactHourNameClass", "contactHourPreviousValue");
            setContactAttribute(contact, request, errors, "contactPhoneNumber", "contactPhoneNumberClass", "contactPhoneNumberPreviousValue");
            setContactAttribute(contact, request, errors, "contactEmail", "contactEmailClass", "contactEmailPreviousValue");


            if (errors.size() == 0) {
                try {
                    service.addContact(contact);
                    resetPreviousValues(request);
                    request.setAttribute("contacts", service.getContacts());
                    return "contacts.jsp";
                } catch (DomainException | DbException exc){
                    errors.add(exc.getMessage());
                    request.setAttribute("errors", errors);
                    return "contacts.jsp";
                }
            } else {
                request.setAttribute("errors", errors);
                return "contacts.jsp";
            }
        }

        return "contacts.jsp";
    }

    private void setContactAttribute(Contact contact, HttpServletRequest request, ArrayList<String> errors,
                                     String parameterName, String className, String prevValue) {
        try {
            String parameterValue = request.getParameter(parameterName);
            switch (parameterName) {
                case "contactFirstName":
                    contact.setFirstName(parameterValue);
                    break;
                case "contactLastName":
                    contact.setLastName(parameterValue);
                    break;
                case "contactDate":
                    contact.setContactDate(parameterValue);
                    break;
                case "contactHour":
                    contact.setContactHour(parameterValue);
                    break;
                case "contactPhoneNumber":
                    contact.setPhoneNumber(parameterValue);
                    break;
                case "contactEmail":
                    contact.setEmail(parameterValue);
                    break;
            }
            request.setAttribute(className, "has-success");
            request.setAttribute(prevValue, parameterValue);
        } catch (DomainException exc){
            errors.add(exc.getMessage());
            request.setAttribute(className, "has-error");
        }
    }

    private void resetPreviousValues(HttpServletRequest request){
        request.setAttribute("contactFirstNamePreviousValue", "");
        request.setAttribute("contactLastNamePreviousValue", "");
        request.setAttribute("contactDatePreviousValue", "");
        request.setAttribute("contactHourPreviousValue", "");
        request.setAttribute("contactPhoneNumberPreviousValue", "");
        request.setAttribute("contactEmailPreviousValue", "");
    }

}
