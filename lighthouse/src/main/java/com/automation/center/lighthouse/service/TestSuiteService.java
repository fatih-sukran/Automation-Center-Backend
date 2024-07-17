package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.TestSuite;
import com.automation.center.lighthouse.repository.TestSuiteRepository;

public class TestSuiteService extends BaseCrud<TestSuite, Long, TestSuiteRepository> {

    public TestSuiteService(TestSuiteRepository repository) {
        super(repository);
    }
}
