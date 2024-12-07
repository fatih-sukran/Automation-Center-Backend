package com.automation.center.lighthouse.base;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.View;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Component
public final class JenkinsUtil {
    @Value("${jenkins.username}")
    private String username;
    @Value("${jenkins.password}")
    private String password;
    @Value("${jenkins.url}")
    private String jenkinsUrl;
    private JenkinsServer jenkinsServer;

    private JenkinsServer getJenkinsServer() {
        if (jenkinsServer == null) {
            var uri = URI.create(jenkinsUrl);
            var jenkinsHttpClient = new JenkinsHttpClient(uri, username, password);
            this.jenkinsServer = new JenkinsServer(jenkinsHttpClient);
        }
        return jenkinsServer;
    }

    @SneakyThrows
    public View getView(String name) {
        return getJenkinsServer().getView(name);
    }

    @SneakyThrows
    public List<View> getAllViews() {
        return getJenkinsServer()
                .getViews()
                .values()
                .stream()
                .toList();
    }

    @SneakyThrows
    public JobWithDetails getJob(String name) {
        return getJenkinsServer().getJob(name);
    }

    @SneakyThrows
    public List<Job> getAllJobs(View view) {
        return getJenkinsServer()
                .getJobs(view.getName())
                .values()
                .stream()
                .toList();
    }

    public List<Job> getAllJobs() {
        var views = getAllViews();
        return views.stream()
                .flatMap(view -> getAllJobs(view).stream())
                .toList();
    }

    @SneakyThrows
    public String buildJob(String jobName) {
        return getJenkinsServer()
                .getJob(jobName)
                .build(true)
                .getQueueItemUrlPart();
    }

    @SneakyThrows
    public String buildJob(Job jenkinsJob) {
        return getJenkinsServer()
                .getJob(jenkinsJob.getName())
                .build(true)
                .toString();
    }

    @SneakyThrows
    public String buildJob(Job jenkinsJob, Map<String, String> parameters) {
        return getJenkinsServer()
                .getJob(jenkinsJob.getName())
                .build(parameters, true)
                .getQueueItemUrlPart();
    }
}
