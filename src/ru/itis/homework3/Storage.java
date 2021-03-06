package ru.itis.homework3;

import java.util.List;
import java.util.Optional;


public interface Storage {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByName(String name);
    Optional<User[]> findAll();
    Optional<User[]> findAllByAge(Short age);
}

