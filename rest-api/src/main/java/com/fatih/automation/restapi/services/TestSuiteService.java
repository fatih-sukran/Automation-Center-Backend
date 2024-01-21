package com.fatih.automation.restapi.services;

import com.fatih.automation.common.model.TestSuite;
import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.repositories.TestClassRepository;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import com.fatih.automation.restapi.repositories.TestSuiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSuiteService extends BaseCrud<TestSuite, Long, TestSuiteRepository> {

    private final TestMethodRepository testMethodRepository;
    private final TestClassRepository testClassRepository;

    public TestSuiteService(TestSuiteRepository repository,
                            TestMethodRepository testMethodRepository,
                            TestClassRepository testClassRepository) {
        super(repository);
        this.testMethodRepository = testMethodRepository;
        this.testClassRepository = testClassRepository;
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
