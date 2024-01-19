package com.fatih.automation.jenkins.model;

import com.fatih.automation.jenkins.enums.BuildStatus;
import com.fatih.automation.jenkins.enums.ResultStatus;

public class TestBuild<T> {
    long id;
    T build;
    BuildStatus buildStatus;
    ResultStatus resultStatus;
}
