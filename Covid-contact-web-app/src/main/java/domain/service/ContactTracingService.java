package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Contact;
import domain.model.Person;

import java.util.List;

public class ContactTracingService {

    private final PersonDBSQL personDB = new PersonDBSQL();

    public ContactTracingService() {
    }

    /* USERS */

    public List<Person> getPersons() {
        return personDB.getAll();
    }

    public void addPerson(Person person) {
        personDB.add(person);
    }

    // TODO: Zorg dat onderstaande methodes werken via de db
    public void updatePersons(Person person) { personDB.update(person); }

    public void deletePerson(String id) {
        personDB.delete(id);
    }

    public Person getPersonIfAuthenticated(String userid, String password){ return personDB.getPersonIfAuthenticated(userid, password);}


    /* CONTACTS */

    public void addContact(Contact contact){
        personDB.addContact(contact);
    }

    public List<Contact> getContacts() {
        return personDB.getAllContacts();
    }

    public void deleteContact(String firstName, String lastName, String contactDate, String contactHour) {
        personDB.deleteContact(firstName, lastName, contactDate, contactHour);
    }

    /* GENERAL */

    private PersonDB getPersonDb() {
        return personDB;
    }



}
