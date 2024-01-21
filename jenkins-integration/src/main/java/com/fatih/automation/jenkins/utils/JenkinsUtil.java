package com.fatih.automation.jenkins.utils;

import com.fatih.automation.jenkins.model.JenkinsJob;
import com.fatih.automation.jenkins.model.JenkinsView;
import com.offbytwo.jenkins.JenkinsServer;
import lombok.SneakyThrows;

import java.io.Closeable;
import java.net.URI;
import java.util.List;

public class JenkinsUtil implements Closeable {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String JENKINS_URL = "http://localhost:8181";
    private final JenkinsServer jenkinsServer;

    public JenkinsUtil() {
        var uri = URI.create(JENKINS_URL);
        jenkinsServer = new JenkinsServer(uri, USERNAME, PASSWORD);
    }

    @SneakyThrows
    public JenkinsView getView(String name) {
        var view = jenkinsServer.getView(name);
        return new JenkinsView(view.getName(), List.of());
    }

    @SneakyThrows
    public List<JenkinsView> getAllViews() {
        return jenkinsServer
                .getViews()
                .values().stream()
                .map(view -> new JenkinsView(view.getName(), List.of()))
                .toList();
    }

    @SneakyThrows
    public JenkinsJob getJob(String name) {
        var job = jenkinsServer.getJob(name);
        return new JenkinsJob(job.getName());
    }

    @SneakyThrows
    public List<JenkinsJob> getAllJobs(JenkinsView view) {
        return jenkinsServer
                .getJobs(view.getName())
                .values()
                .stream()
                .map(job -> new JenkinsJob(job.getName()))
                .toList();
    }

    public List<JenkinsJob> getAllJobs() {
        var views = getAllViews();
        return views.stream()
                .flatMap(view -> getAllJobs(view).stream())
                .toList();
    }

    @SneakyThrows
    public String buildJob(JenkinsJob jenkinsJob) {
        return jenkinsServer.getJob(jenkinsJob.getName()).build(true).getQueueItemUrlPart();
    }

    @Override
    public void close() {
        jenkinsServer.close();
    }
}
