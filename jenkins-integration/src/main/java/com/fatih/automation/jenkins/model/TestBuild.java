package com.fatih.automation.jenkins.model;

import com.fatih.automation.common.model.TestMethod;
import com.fatih.automation.jenkins.enums.BuildStatus;
import com.fatih.automation.jenkins.enums.ResultStatus;

public class TestBuild {
    long id;
    TestMethod testMethod;
    BuildStatus buildStatus;
    ResultStatus resultStatus;
}
