package com.fatih.automation.controller;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.SneakyThrows;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TestMethodController {

    private static final String JENKINS_URL = "http://localhost:8080";
    private final TestMethodRepository testMethodRepository;
    private final JenkinsServer jenkinsServer;
    private final Map<String, QueueReference> jobBuilds = new HashMap<>();

    @SneakyThrows
    public TestMethodController(TestMethodRepository testMethodRepository) {
        this.testMethodRepository = testMethodRepository;
        this.jenkinsServer = new JenkinsServer(new URI(JENKINS_URL), "admin", "admin");
    }

    @GetMapping("/getTestMethods")
    public List<String> test() {
        return testMethodRepository.findAll().stream().map(TestMethod::getName).toList();
    }

    @SneakyThrows
    @GetMapping("/run/test/{id}")
    public String  runTest(@PathVariable Long id) {
        var views = jenkinsServer.getViews();
        var jsonObject = new JSONObject();
        for (var view : views.entrySet()) {
            var job =  view.getValue().getJobs().get(0);
            var buildDetail = job.build(true);
            var uuid = UUID.randomUUID();
            jsonObject.put("build_id", uuid);
            jsonObject.put("view_name", view.getKey());
            jsonObject.put("job_name", job.getName());
            jobBuilds.put(uuid.toString(), buildDetail);
        }
        return jsonObject.toString();
    }

    @SneakyThrows
    @GetMapping("/test/status/{id}")
    public String getStatus(@PathVariable String id) {
        var queueReference = jobBuilds.get(id);
        if (queueReference != null) {
            var queueItem = jenkinsServer.getQueueItem(queueReference);
            var build = jenkinsServer.getBuild(queueItem);
            return build.details().getResult().name();
        }

        return "null" + id;
    }
}
