package com.fatih.automation.core;

public interface IJenkinsJobController {

    void runTest();
    String getStatus();
    String getTestResult();
}
