package com.fatih.automation.jenkins.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatih.automation.common.model.TestBuild;
import com.fatih.automation.jenkins.mapper.BuildStatusMapper;
import com.fatih.automation.jenkins.model.JenkinsJob;
import com.fatih.automation.jenkins.model.JenkinsView;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.BaseModel;
import com.offbytwo.jenkins.model.Build;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.apache.commons.lang.NotImplementedException;

import java.net.URI;
import java.util.List;
import java.util.Map;

public final class JenkinsUtil {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String JENKINS_URL = "http://localhost:8181";
    private static final URI uri = URI.create(JENKINS_URL);
    private static final JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(uri, USERNAME, PASSWORD);
    private static final JenkinsServer jenkinsServer = new JenkinsServer(jenkinsHttpClient);

    @SneakyThrows
    public static JenkinsView getView(String name) {
        var view = jenkinsServer.getView(name);
        return new JenkinsView(view.getName(), List.of());
    }

    @SneakyThrows
    public static List<JenkinsView> getAllViews() {
        return jenkinsServer
                .getViews()
                .values().stream()
                .map(view -> new JenkinsView(view.getName(), List.of()))
                .toList();
    }

    @SneakyThrows
    public static JenkinsJob getJob(String name) {
        var job = jenkinsServer.getJob(name);
        return new JenkinsJob(job.getName());
    }

    @SneakyThrows
    public static List<JenkinsJob> getAllJobs(JenkinsView view) {
        return jenkinsServer
                .getJobs(view.getName())
                .values()
                .stream()
                .map(job -> new JenkinsJob(job.getName()))
                .toList();
    }

    public static List<JenkinsJob> getAllJobs() {
        var views = getAllViews();
        return views.stream()
                .flatMap(view -> getAllJobs(view).stream())
                .toList();
    }

    public static String buildJob(String jobName) {
        // TODO: Implement
        throw new NotImplementedException();
    }

    @SneakyThrows
    public static Integer buildJob(JenkinsJob jenkinsJob, Map<String, String> parameters) {
        var queueReference = jenkinsServer
                .getJob(jenkinsJob.getName())
                .build(parameters, true)
                .getQueueItemUrlPart();
        var parts = queueReference.split("\\D+");
        return parts.length > 0 ? Integer.parseInt(parts[parts.length - 1]) : null;
    }

    @SneakyThrows //todo: return queue id
    public static String buildJob(JenkinsJob jenkinsJob) {
        return jenkinsServer
                .getJob(jenkinsJob.getName())
                .build(true)
                .getQueueItemUrlPart();
    }

    @SneakyThrows
    public static TestBuild updateBuildStatus(TestBuild testBuild) {
        var url = "/job/Automation%20Center/api/json?tree=builds[number,result,queueId,url]";
        var build = jenkinsHttpClient.get(url, ListBuild.class)
                .getBuilds().stream()
                .filter(b -> b.getQueueId() == testBuild.getQueueId())
                .findFirst();
        if (build.isPresent()) {
            build.get().setClient(jenkinsHttpClient);
            var buildResult = build.get().details().getResult();
            var buildStatus = BuildStatusMapper.map(buildResult);
            if (buildStatus != testBuild.getBuildStatus()) {
                testBuild.setBuildStatus(buildStatus);
            }
        }
        return testBuild;
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class ListBuild extends BaseModel {
    @JsonProperty("builds")
    private List<Build> builds;
}
