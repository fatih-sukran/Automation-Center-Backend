package com.fatih.automation.data;

import com.offbytwo.jenkins.model.Build;
import lombok.SneakyThrows;

public enum TestStatus {
    PASSED,
    FAILED,
    SKIPPED,
    RUNNING;

    @SneakyThrows
    public static TestStatus fromJenkinsBuild(Build build) {
        if (build.details().isBuilding()) {
            return RUNNING;
        }

        build.getTestResult();
        return null;
    }
}
