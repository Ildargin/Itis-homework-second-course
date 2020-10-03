package ru.itis.repositories;

import ru.itis.models.User;
import java.sql.Connection;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(User entity) {

    }
}
