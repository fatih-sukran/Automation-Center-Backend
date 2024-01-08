package com.fatih.automation.core;

import java.util.List;
import java.util.Optional;

public interface BaseCrud<T, ID> {

    T save(T t);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T t);
    void deleteById(ID id);
}
