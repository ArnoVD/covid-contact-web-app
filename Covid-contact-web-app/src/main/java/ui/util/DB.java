package ui.util;

import domain.model.Person;

import java.sql.*;
import java.util.Properties;

public class DB {
    public static void main(String[] args) throws SQLException {
        // set properties for the db connection
        Properties properties = new Properties();
        // in what follows: replace "webontwerp" by the name of your database
        // replace "web3" by the name of your schema
        String url = "jdbc:postgresql://databanken.ucll.be:62021/2TX33";
        String schema = "web3_project_r0712776";
        try {
            Class.forName("ui.util.Secret"); // implementation of abstract class Credentials
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class ui.view.Secret with credentials not found!");
        }
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "prefer");
        // open the db connection
        Connection connection = DriverManager.getConnection(url, properties);
        // first an example for execute: set the search_path for this connection
        String querySetSearchPath = "SET search_path = web3_project_r0712776;";
        Statement statement = connection.createStatement();
        statement.execute(querySetSearchPath);
        // get all the countries
        String query = String.format("SELECT * from %s.person;", schema);
        statement.executeQuery(query);
        ResultSet result = statement.getResultSet();

        query = String.format("insert into %s.person (userid, firstname, lastname, email, password) values ('23245', 'Arno', 'Vandijck', 'vandijckarno@hotmail.com', '123')", schema) ;
        statement.execute(query);

        while (result.next()) {
            String userid = result.getString("userid");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String email = result.getString("email");
            String password = result.getString("password");
            try { // validation of data stored in database
                Person person = new Person(userid, firstname, lastname, email, password);
                System.out.println(person.toString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());}}
        statement.close();
        connection.close(); // close the db connection
    }
}