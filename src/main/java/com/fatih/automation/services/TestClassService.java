package com.fatih.automation.services;

import com.fatih.automation.core.BaseCrud;
import com.fatih.automation.model.TestClass;
import com.fatih.automation.repositories.TestClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestClassService implements BaseCrud<TestClass, Long> {

    private final TestClassRepository repository;

    @Override
    public TestClass save(TestClass testClass) {
        return repository.save(testClass);
    }

    @Override
    public Optional<TestClass> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<TestClass> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TestClass testClass) {
        repository.delete(testClass);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}