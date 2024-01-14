package com.fatih.automation.restapi.services;

import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.model.TestSuite;
import com.fatih.automation.restapi.repositories.TestClassRepository;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import com.fatih.automation.restapi.repositories.TestSuiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestSuiteService implements BaseCrud<TestSuite, Long> {

    private final TestSuiteRepository repository;
    private final TestMethodRepository testMethodRepository;
    private final TestClassRepository testClassRepository;

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

    public TestSuite addTestMethods(Long testSuiteId, List<Long> testMethodIds) {
        var testSuite = repository
                .findById(testSuiteId)
                .orElseThrow(() -> new RuntimeException("Test Suite not found with id: " + testSuiteId));
        var testMethods = testMethodRepository.findAllById(testMethodIds);

        testMethods.stream()
                .filter(testMethod -> !testSuite.getTestMethods().contains(testMethod))
                .forEach(testMethod -> testSuite.getTestMethods().add(testMethod));
        return repository.save(testSuite);
    }

    public TestSuite addTestClasses(Long testSuiteId, List<Long> testClassIds) {
        var testSuite = repository
                .findById(testSuiteId)
                .orElseThrow(() -> new RuntimeException("Test Suite not found with id: " + testSuiteId));
        var testClasses = testClassRepository.findAllById(testClassIds);

        testClasses.stream()
                .filter(testClass -> !testSuite.getTestClasses().contains(testClass))
                .forEach(testClass -> testSuite.getTestClasses().add(testClass));
        return repository.save(testSuite);
    }
}
