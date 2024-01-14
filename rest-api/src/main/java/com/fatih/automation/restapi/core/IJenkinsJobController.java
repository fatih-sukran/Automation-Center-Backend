package com.fatih.automation.restapi.core;

import com.fatih.automation.restapi.data.TestStatus;
import com.fatih.automation.restapi.model.TestBuild;
import com.fatih.automation.restapi.model.TestMethod;

import java.util.UUID;

public interface IJenkinsJobController {

    void runTest(long id);

    TestBuild runTest(TestMethod testMethod);

    TestStatus getStatus(UUID uuid);
}
