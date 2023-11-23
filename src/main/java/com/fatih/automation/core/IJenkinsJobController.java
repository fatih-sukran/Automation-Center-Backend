package com.fatih.automation.core;

import com.fatih.automation.core.data.TestStatus;
import com.fatih.automation.core.model.TestBuild;
import com.fatih.automation.core.model.TestMethod;

import java.util.UUID;

public interface IJenkinsJobController {

    void runTest(long id);
    TestBuild runTest(TestMethod testMethod);
    TestStatus getStatus(UUID uuid);
}
