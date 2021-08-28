package com.tabdelbari.jwtdemo.service;

import java.util.List;

public interface RestService<T, C> {

    T get(C id);
    List<T> getAll();
    T save(T entity);
    T update(C id, T entity);
    void delete(C id);
    void deleteAll();

}
