package domain.db;

<<<<<<< HEAD
import domain.model.Contact;
=======
>>>>>>> origin/main
import domain.model.Person;
import ui.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class PersonDBSQL implements PersonDB {
=======
public class PersonDBSQL implements PersonDB{
>>>>>>> origin/main
    private Connection connection;
    private final String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    /**
     * Stores the given person in the database
     *
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person person) {

        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.person (userid, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = getConnection().prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getFirstName());
            statementSQL.setString(3, person.getLastName());
            statementSQL.setString(4, person.getEmail());
            statementSQL.setString(5, person.getPassword());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
<<<<<<< HEAD
    public void update(Person person) {
        // NOT YET IMPLEMENTED
    }

    @Override
    public void delete(String personId) {
        if(personId == null){
            throw new DbException("No id given");
        }

        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", this.schema);

        try {
            PreparedStatement statementSQL = getConnection().prepareStatement(sql);
            statementSQL.setString(1, personId);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }

    }

    @Override
    public Person searchPerson(String userid) {
        if(userid == null){
            throw new DbException("User id not found.");
        }

        Person person = null;
        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userID = result.getString("userid");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                person = new Person(userID, firstName, lastName, email, password);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return person;
    }

    @Override
    public Person getPersonIfAuthenticated(String userid, String password) {
        Person person = searchPerson(userid);
        if(person != null && person.getPassword().equals(password))
            return person;
        return null;
    }

    @Override
    public void addContact(Contact contact) {

        if (contact == null) {
            throw new DbException("Nothing to add.");
        }

        String sql = String.format("INSERT INTO %s.contact (firstname, lastname, email, phone, contactdate, contacthour) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = getConnection().prepareStatement(sql);
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setString(4, contact.getPhoneNumber());
            statementSQL.setString(5, contact.getContactDate());
            statementSQL.setInt(6, contact.getContactHour());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAllContacts() {

        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);

        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phoneNumber = result.getString("phone");
                String contactDate = result.getString("contactdate");
                String contactHour = result.getString("contacthour");
                Contact contact = new Contact(firstName, lastName, contactDate, contactHour, phoneNumber, email);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public void deleteContact(String firstName, String lastName, String contactDate, String contactHour) {
        if(firstName == null || lastName == null || contactDate == null || contactHour == null){
            throw new DbException("Something is missing.");
        }

        String sql = String.format("DELETE FROM %s.contact WHERE firstname = ? AND lastname = ? AND contactdate = ? AND contacthour = ?", this.schema);

        try {
            PreparedStatement statementSQL = getConnection().prepareStatement(sql);
            statementSQL.setString(1, firstName);
            statementSQL.setString(2, lastName);
            statementSQL.setString(3, contactDate);
            statementSQL.setInt(4, Integer.parseInt(contactHour));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }

    }

    @Override
    public Person get(String personId) {
        return null;
    }

    @Override
=======
>>>>>>> origin/main
    public List<Person> getAll() {

        List<Person> persons = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                Person person = new Person(userid, firstName, lastName, email, password);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }


    /**
     * Check the connection and reconnect when necessery
     *
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    /**
     * Check if the connection is still open
     * When connection has been closed: reconnect
     */
    private void checkConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection has been closed");
                this.reConnect();
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    /**
     * Reconnects application to db
     */
    private void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.reconnect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }
}
