package com.automation.center.jenkins.repository;

import com.automation.center.jenkins.mapper.JobMapper;
import com.automation.center.jenkins.model.Job;
import com.automation.center.jenkins.util.JenkinsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobRepository {
    private final JenkinsManager jenkins;
    private final JobMapper mapper;

    public Job create(Job job) {
        try {
            var xml = jenkins.getJobXml(job.getDescription(), "");
            jenkins.getServer().createJob(job.getName(), xml, true);
            return getJobByName(job.getName());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create job: " + job.getName(), e);
        }
    }

    public Job getJobByName(String name) {
        try {
            var job = jenkins.getServer().getJob(name);
            return mapper.map(job);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get job: " + name, e);
        }
    }

    public List<Job> getAllJobs() {
        try {
            return jenkins.getServer().getJobs().values().stream().map(mapper::map).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get all jobs", e);
        }
    }

    public void deleteJob(String name) {
        try {
            jenkins.getServer().deleteJob(name, true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete job: " + name, e);
        }
    }
}
