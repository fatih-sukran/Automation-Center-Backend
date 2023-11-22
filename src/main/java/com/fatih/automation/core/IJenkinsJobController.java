package com.fatih.automation.core;

import com.fatih.automation.core.data.TestStatus;
import com.fatih.automation.core.model.TestBuild;
import com.fatih.automation.core.model.TestMethod;

public interface IJenkinsJobController {

    TestBuild runTest(long id);
    TestBuild runTest(TestMethod testMethod);
    TestStatus getStatus(TestBuild testBuild);
}
