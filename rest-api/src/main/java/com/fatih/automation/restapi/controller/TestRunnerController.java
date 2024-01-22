package com.fatih.automation.restapi.controller;

import com.fatih.automation.common.enums.ResultStatus;
import com.fatih.automation.common.model.TestBuild;
import com.fatih.automation.common.model.build.ClassBuild;
import com.fatih.automation.jenkins.testng.XmlSuiteGenerator;
import com.fatih.automation.jenkins.utils.JenkinsUtil;
import com.fatih.automation.restapi.services.TestBuildService;
import com.fatih.automation.restapi.services.TestClassService;
import com.fatih.automation.restapi.services.build.ClassBuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/testrunner")
@RequiredArgsConstructor
public class TestRunnerController {
    private final TestClassService testClassService;
    private final ClassBuildService classBuildService;
    private final TestBuildService testBuildService;
    private static final String JOB_NAME = "Automation Center";

    @PostMapping("/{classId}")
    public ResponseEntity<TestBuild> runTestClass(@PathVariable Long classId) {
        var job = JenkinsUtil.getJob(JOB_NAME);
        var testClass = testClassService.findById(classId).orElseThrow();
        var xmlSuiteText = new XmlSuiteGenerator().addTestClass(testClass).generate();
        var queueId = JenkinsUtil.buildJob(job, Map.of("xmlSuite", xmlSuiteText));

        var testBuild = new TestBuild()
                .setQueueId(queueId);
        var savedTestBuild = testBuildService.save(testBuild);

        var classBuild = new ClassBuild()
                .setTestClass(testClass)
                .setTestBuild(savedTestBuild)
                .setResultStatus(ResultStatus.UNKNOWN);
        classBuildService.save(classBuild);

        return ResponseEntity.ok(savedTestBuild);
    }

    @GetMapping("/{buildId}")
    public ResponseEntity<Optional<TestBuild>> findById(@PathVariable Long buildId) {
        var testBuild = testBuildService.findById(buildId);
        if (testBuild.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        testBuildService.updateStatus(testBuild.get());
        return ResponseEntity.ok(testBuild);
    }

    @GetMapping
    public ResponseEntity<List<TestBuild>> findAll() {
        return ResponseEntity.ok(testBuildService.findAll());
    }
}
