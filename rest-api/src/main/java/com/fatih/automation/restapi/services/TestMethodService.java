package com.fatih.automation.restapi.services;

import com.fatih.automation.common.model.TestMethod;
import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class TestMethodService extends BaseCrud<TestMethod, Long, TestMethodRepository> {

    public TestMethodService(TestMethodRepository repository) {
        super(repository);
    }
}
