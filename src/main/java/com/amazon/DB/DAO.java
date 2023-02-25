package com.amazon.DB;

import java.util.List;
//DAO database access object
// CRUD operations on the table
// generic interface so that different classes can use same interface
public interface DAO<T> {
    int insert(T object);
    int update(T object);
    int delete(T object);
    List<T> retrieve();
    List<T> retrieve(String sql);
}

