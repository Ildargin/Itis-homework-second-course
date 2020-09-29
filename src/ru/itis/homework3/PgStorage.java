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
            System.out.println("You successfully connected to database now" + "\n");
        } else System.out.println("Failed to make connection to database");

    }

    public Long getAllLength() {
        ResultSet rs;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) FROM users");
            rs.next();
            Long num = rs.getLong("count");
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot get length");
            return 0L;
        }

    }

    public int getQueryLength(String Query) {
        ResultSet rs;
        int size = 0;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(Query);
            while (rs.next()) {
                size++;
            }
            return size;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setToArray(ResultSet rs, User[] tmp, long size) throws SQLException {
        long id;
        short age;
        String firstName;
        String lastName;
        for (int i = 0; i < size; i++) {
            rs.next();
            id = rs.getLong("id");
            age = rs.getShort("age");
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            tmp[i] = (new User(id, age, firstName, lastName));
        }
        rs.close();
        statement.close();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        ResultSet rs;
        String firstName = "", lastName = "";
        short age;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users WHERE id =" + id.toString());
            rs.next();
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            age = rs.getShort("age");
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exeption");
            return Optional.empty();
        }
        User result = new User(id, age, firstName, lastName);
        return Optional.of(result);
    }

    @Override
    public Optional<User> findUserByName(String firstName) {
        ResultSet rs;
        long id;
        short age;
        String lastName = "";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users WHERE first_name=" + "\'" + firstName + "\'");
            rs.next();
            age = rs.getShort("age");
            id = rs.getLong("id");
            lastName = rs.getString("last_name");
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exeption");
            return Optional.empty();
        }
        User result = new User(id, age, firstName, lastName);
        return Optional.of(result);
    }

    @Override
    public Optional<User[]> findAll() {
        ResultSet rs;
        String lastName;
        String firstName;
        long id;
        short age;
        User[] tmp;
        try {
            long size = getAllLength();
            rs = statement.executeQuery("SELECT * FROM users");
            tmp = new User[(int) size];
            setToArray(rs, tmp, size);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();

        }
        return Optional.of(tmp);
    }

    @Override
    public Optional<User[]> findAllByAge(Short age) {
        ResultSet rs;
        String lastName, firstName = "";
        User[] result;
        long id;
        String query = "SELECT * FROM users WHERE age=" + age.toString();
        int queryLen = getQueryLength(query);
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = new User[queryLen];
            setToArray(rs, result, queryLen);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(result);

    }
}

