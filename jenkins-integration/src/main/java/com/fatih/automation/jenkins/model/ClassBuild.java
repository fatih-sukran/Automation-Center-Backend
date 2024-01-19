package com.fatih.automation.jenkins.model;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.jenkins.enums.BuildStatus;
import com.fatih.automation.jenkins.enums.ResultStatus;

public class ClassBuild {
    long id;
    TestClass testClass;
    BuildStatus buildStatus;
    ResultStatus resultStatus;
}
