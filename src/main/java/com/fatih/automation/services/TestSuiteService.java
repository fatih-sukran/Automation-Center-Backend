package com.fatih.automation.services;

import com.fatih.automation.core.BaseCrud;
import com.fatih.automation.model.TestSuite;
import com.fatih.automation.repositories.TestMethodRepository;
import com.fatih.automation.repositories.TestSuiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestSuiteService implements BaseCrud<TestSuite, Long> {

    private final TestSuiteRepository repository;
    private final TestMethodRepository testMethodRepository;

    @Override
    public TestSuite save(TestSuite testSuite) {
        return repository.save(testSuite);
    }

    @Override
    public Optional<TestSuite> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<TestSuite> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TestSuite testSuite) {
        repository.delete(testSuite);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public TestSuite addTestMethod(Long testSuiteId, List<Long> testMethodIds) {
        var testSuite = repository
                .findById(testSuiteId)
                .orElseThrow(() -> new RuntimeException("Test Suite not found with id: " + testSuiteId));
        var testMethods = testMethodRepository.findAllById(testMethodIds);

        testMethods.stream()
                .filter(testMethod -> !testSuite.getTestMethods().contains(testMethod))
                .forEach(testMethod -> testSuite.getTestMethods().add(testMethod));
        return repository.save(testSuite);
    }
}
