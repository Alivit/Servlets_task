package com.example.checkrunner.dao;

import java.util.List;

public interface Repository<T> {
    T add(T entity);

    List<T> getAll();

    T update(T entity);

    T remove(T entity);
}
