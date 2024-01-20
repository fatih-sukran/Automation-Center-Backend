package com.fatih.automation.jenkins.model;

import com.fatih.automation.jenkins.enums.BuildStatus;

import java.util.List;

public class SuiteBuild {
    long id;
    List<TestBuild> testBuilds;
    BuildStatus buildStatus;
}
