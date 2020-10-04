package ru.itis.repositories;

import ru.itis.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(User entity) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO Users (first_name, last_name, password, email) VALUES (?, ?, ?, ?);");
            st.setString(1, entity.getFirstName());
            st.setString(2, entity.getLastName());
            st.setObject(3, entity.getPassword());
            st.setObject(4, entity.getEmail());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
