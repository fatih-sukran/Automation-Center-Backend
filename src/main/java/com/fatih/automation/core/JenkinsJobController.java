package com.fatih.automation.core;

import com.fatih.automation.core.data.TestStatus;
import com.fatih.automation.core.model.TestBuild;
import com.fatih.automation.core.model.TestMethod;
import com.offbytwo.jenkins.JenkinsServer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JenkinsJobController implements IJenkinsJobController {

    private static final String JOB_NAME = "Automation";
    private static final String JENKINS_URL = "http://localhost:8081";
    private static final String JENKINS_USER = "admin";
    private static final String JENKINS_PASSWORD = "admin";
    private static final Map<UUID, TestBuild> JOB_BUILDS = new HashMap<>();
    private final JenkinsServer JENKINS_SERVER = new JenkinsServer(new URI(JENKINS_URL), JENKINS_USER, JENKINS_PASSWORD);


    public JenkinsJobController() throws URISyntaxException {}

    @SneakyThrows
    @Override
    public TestBuild runTest(long id) {
        JENKINS_SERVER.getJob(JOB_NAME).build(true);
        return null;
    }

    @Override
    public TestBuild runTest(TestMethod testMethod) {
        JENKINS_SERVER.getJob(JOB_NAME).build(true);
        return null;
    }

    @Override
    public TestStatus getStatus(TestBuild testBuild) {
        return null;
    }
}
