package com.fatih.automation.restapi.services;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.repositories.TestClassRepository;
import org.springframework.stereotype.Service;

@Service
public class TestClassService extends BaseCrud<TestClass, Long, TestClassRepository> {

    public TestClassService(TestClassRepository repository) {
        super(repository);
    }
}
