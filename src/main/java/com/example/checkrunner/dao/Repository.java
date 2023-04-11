package com.example.checkrunner.dao;

import java.util.List;

public interface Repository<T> {
    int add(T entity);

    T find(int id);

    List<T> getAll();

    int update(T entity);

    int remove(T entity);
}
