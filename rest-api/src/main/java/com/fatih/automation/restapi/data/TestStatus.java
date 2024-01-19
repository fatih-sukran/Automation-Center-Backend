package com.fatih.automation.restapi.data;

public enum TestStatus {
    PASSED,
    FAILED,
    SKIPPED,
    RUNNING;

//    @SneakyThrows
//    public static TestStatus fromJenkinsBuild(Build build) {
//        if (build.details().isBuilding()) {
//            return RUNNING;
//        }
//
//        build.getTestResult();
//        return null;
//    }
}
