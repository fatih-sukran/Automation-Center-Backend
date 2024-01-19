package com.fatih.automation.jenkins.model;

import com.fatih.automation.common.model.TestSuite;
import com.fatih.automation.jenkins.enums.BuildStatus;
import com.fatih.automation.jenkins.enums.ResultStatus;

public class SuiteBuild {
    long id;
    TestSuite testSuite;
    BuildStatus buildStatus;
    ResultStatus resultStatus;
}
