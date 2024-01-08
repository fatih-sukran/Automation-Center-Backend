package com.fatih.automation.services;

import com.fatih.automation.core.BaseCrud;
import com.fatih.automation.model.TestMethod;
import com.fatih.automation.repositories.TestMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestMethodService implements BaseCrud<TestMethod, Long> {

    private final TestMethodRepository repository;

    @Override
    public TestMethod save(TestMethod testMethod) {
        return repository.save(testMethod);
    }

    @Override
    public Optional<TestMethod> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<TestMethod> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TestMethod testMethod) {
        repository.delete(testMethod);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
