package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Locale;

public class Register extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        // If sign up button is clicked
        if (request.getParameter("signUp") != null)
        {
            ArrayList<String> errors = new ArrayList<>();

            Person person = new Person();
            setPersonAttribute(person, request, errors, "userid", "useridClass", "useridPreviousValue");
            setPersonAttribute(person, request, errors, "firstName", "firstNameClass", "firstNamePreviousValue");
            setPersonAttribute(person, request, errors, "lastName", "lastNameClass", "lastNamePreviousValue");
            setPersonAttribute(person, request, errors, "email", "emailClass", "emailPreviousValue");
            setPersonAttribute(person, request, errors, "password", "passwordClass", "passwordPreviousValue");


            if (errors.size() == 0) {
                try {
                    service.addPerson(person);
                    return "index.jsp";
                } catch (DomainException | DbException exc){
                    errors.add(exc.getMessage());
                    request.setAttribute("errors", errors);
                    return "register.jsp";
                }
            } else {
                request.setAttribute("errors", errors);
                return "register.jsp";
            }
        }

        return "register.jsp";
    }

    private void setPersonAttribute(Person person, HttpServletRequest request, ArrayList<String> errors,
                           String parameterName, String className, String prevValue) {
        try {
            String parameterValue = request.getParameter(parameterName);
            switch (parameterName) {
                case "userid":
                    person.setUserid(parameterValue.toLowerCase(Locale.ROOT));
                    break;
                case "firstName":
                    person.setFirstName(parameterValue);
                    break;
                case "lastName":
                    person.setLastName(parameterValue);
                    break;
                case "email":
                    person.setEmail(parameterValue);
                    break;
                case "password":
                    person.setPassword(parameterValue);
                    break;
            }
            request.setAttribute(className, "has-success");
            request.setAttribute(prevValue, parameterValue);
        } catch (DomainException exc){
            errors.add(exc.getMessage());
            request.setAttribute(className, "has-error");
        }
    }

}
