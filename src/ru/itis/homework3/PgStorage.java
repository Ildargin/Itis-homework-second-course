package ru.itis.homework3;

import java.sql.*;
import java.util.Optional;

public class PgStorage implements Storage {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/java-crud";
    static final String USER = "postgres";
    static final String PASS = "franzefer";
    private static Connection connection;
    private static Statement statement;

    public void checkDriver() {
        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
    }

    public void createConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else System.out.println("Failed to make connection to database");

    }

    @Override
    public Optional<User> findUserById(Long id) {
        ResultSet rs;
        String firstName = "", lastName = "";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users WHERE id =" + id.toString());
            rs.next();
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exeption");
            return Optional.empty();
        }
        User result = new User(id, firstName, lastName);
        return Optional.of(result);
    }

    @Override
    public Optional<User> findUserByName(String firstName) {
        ResultSet rs;
        long id;
        String lastName = "";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users WHERE first_name=" + "\'" + firstName + "\'");
            rs.next();
            id = rs.getLong("id");
            lastName = rs.getString("last_name");
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exeption");
            return Optional.empty();
        }
        User result = new User(id, firstName, lastName);
        return Optional.of(result);
    }

    @Override
    public Optional<User[]> findAll() {
        ResultSet rs;
        ResultSet rsTwo;
        String lastName;
        String firstName;
        long id;
        User[] tmp;

        try {
            statement = connection.createStatement();
            rsTwo = statement.executeQuery("SELECT COUNT(*) FROM users");
            rsTwo.next();
            int size = rsTwo.getInt("count");
            rs = statement.executeQuery("SELECT * FROM users");
            tmp = new User[size];
            for (int i = 0 ; i < size ; i++){
                rs.next();
                id = rs.getLong("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("first_name");
                tmp[i] = (new User(id, firstName, lastName));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exeption");
            return Optional.empty();

        }
        return Optional.of(tmp);
    }
}

