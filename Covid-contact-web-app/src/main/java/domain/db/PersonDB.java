package domain.db;

<<<<<<< HEAD
import domain.model.Contact;
=======
>>>>>>> origin/main
import domain.model.Person;

import java.util.List;

public interface PersonDB {
<<<<<<< HEAD
    /* USERS */

    Person get(String personId);

    List<Person> getAll();

    void add(Person person);

    void update(Person person);

    void delete(String personId);

    Person searchPerson(String userid);

    Person getPersonIfAuthenticated(String userid, String password);

    /* CONTACTS */

    void addContact(Contact contact);

    List<Contact> getAllContacts();

    void deleteContact(String firstName, String lastName, String contactDate, String contactHour);
=======

    /**
     * Stores the given Person
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Person person);


    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */

    List<Person> getAll();

>>>>>>> origin/main
}
