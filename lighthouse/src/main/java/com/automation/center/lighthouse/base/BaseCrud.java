package com.automation.center.lighthouse.base;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseCrud<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    public T save(T t) {
        return repository.save(t);
    }

    public T findById(ID id) {
        return repository.findById(id).orElseThrow();
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
