package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String firstName;
    private String lastName;
    private String contactDate;
    private int contactHour;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String date, String hour, String phoneNumber, String email){
        setFirstName(firstName);
        setLastName(lastName);
        setContactDate(date);
        setContactHour(hour);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public Contact(){
        // Default constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new DomainException("No first name given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new DomainException("No last name given");
        }
        this.lastName = lastName;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        if(contactDate.isEmpty()){
            throw new DomainException("No contact date given");
        }
        this.contactDate = contactDate;
    }

    public int getContactHour() {
        return contactHour;
    }

    public void setContactHour(String contactHour) {
        if(contactHour.isEmpty()){
            throw new DomainException("No contact hour given");
        }

        int contactHourNumber;

        try {
            contactHourNumber = Integer.parseInt(contactHour);
        } catch (NumberFormatException e){
            throw new DomainException("Contact hour is not a number");
        }

        this.contactHour = contactHourNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.isEmpty()){
            throw new DomainException("No phone number given");
            // 0479341128 == 10
            // +32479341128 == 12
        } else if (phoneNumber.length() == 10 || phoneNumber.length() == 12 ) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new DomainException("Phone number not valid");
        }


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new DomainException("No email given");
        }

        String USERID_PATTERN =
                "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new DomainException("Email not valid");
        }

        this.email = email;
    }
}
