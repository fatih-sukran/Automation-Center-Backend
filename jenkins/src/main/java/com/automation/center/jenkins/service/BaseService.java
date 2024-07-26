package com.automation.center.jenkins.service;

import java.util.List;

public interface BaseService<T> {
    T get(String name);

    List<T> getAll();

    void delete(String name);

    void deleteAll();

    T save(T t);

    T update(T t);
}
