package ru.itis.repositories;


public interface CrudRepository<T> {
    void save(T entity);
}

