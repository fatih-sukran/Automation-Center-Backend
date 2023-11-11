package com.fatih.automation.controller;

import com.offbytwo.jenkins.JenkinsServer;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Data
@RestController
public class TestMethodController {

    private static final String JENKINS_URL = "http://localhost:8181";
    private final TestMethodRepository testMethodRepository;
    private final JenkinsServer jenkinsServer;

    @SneakyThrows
    public TestMethodController(TestMethodRepository testMethodRepository) {
        this.testMethodRepository = testMethodRepository;
        this.jenkinsServer = new JenkinsServer(new URI(JENKINS_URL), "automation-center", "admin");
    }

    @GetMapping("/getTestMethods")
    public String test() {
        StringBuilder result = new StringBuilder();
        for (var testMethod : testMethodRepository.findAll()) {
            result.append(testMethod.getName()).append("<br>");
        }
        return result.toString();
    }

    @SneakyThrows
    @GetMapping("/run/test/{id}")
    public String  runTest(@PathVariable Long id) {
        var sb = new StringBuilder();
        var views = jenkinsServer.getViews();
        for (var view : views.entrySet()) {
            var name =  view.getValue().getName();
            var description = view.getValue().getDescription();
            var url = view.getValue().getUrl();
            var jobs = view.getValue().getJobs();
            sb
                    .append("View Name: ").append(name).append("<br>")
                    .append("View Description: ").append(description).append("<br>")
                    .append("View URL: ").append(url).append("<br><br>");
            for (var job : jobs) {
                sb
                        .append("Job Name: ").append(job.getName()).append("<br>")
                        .append("Job FullName: ").append(job.getFullName()).append("<br>")
                        .append("Job URL: ").append(job.getUrl()).append("<br><br>");
               sb.append("queueItem: ").append(job.build(true).getQueueItemUrlPart());
            }

        }
        return sb.toString();
    }
}
