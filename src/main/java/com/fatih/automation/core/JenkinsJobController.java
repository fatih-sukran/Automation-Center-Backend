package com.fatih.automation.core;

import com.offbytwo.jenkins.JenkinsServer;
import lombok.SneakyThrows;

import java.net.URI;

public class JenkinsJobController implements IJenkinsJobController {

    private final JenkinsServer JENKINS_SERVER;
    private final String JOB_NAME;

    @SneakyThrows
    public JenkinsJobController(String jobName) {
        JENKINS_SERVER = new JenkinsServer(new URI("http://localhost:8081"), "admin", "admin");
        JOB_NAME = jobName;
    }

    @Override
    public void runTest() {

    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public String getTestResult() {
        return null;
    }
}
