package com.fatih.automation.core;

import com.fatih.automation.data.TestStatus;
import com.fatih.automation.model.TestBuild;
import com.fatih.automation.model.TestMethod;

import java.util.UUID;

public interface IJenkinsJobController {

    void runTest(long id);
    TestBuild runTest(TestMethod testMethod);
    TestStatus getStatus(UUID uuid);
}
