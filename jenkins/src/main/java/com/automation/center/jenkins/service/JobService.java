package com.automation.center.jenkins.service;

import com.automation.center.jenkins.model.Job;
import com.automation.center.jenkins.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService implements BaseService<Job> {
    private final JobRepository jobRepository;

    @Override
    public Job get(String name) {
        return jobRepository.getJobByName(name);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.getAllJobs();
    }

    @Override
    public void delete(String name) {
        jobRepository.deleteJob(name);
    }

    @Override
    public void deleteAll() {
        var jobs = jobRepository.getAllJobs();
        jobs.forEach(job -> jobRepository.deleteJob(job.getName()));
    }

    @Override
    public Job save(Job job) {
        return jobRepository.create(job);
    }

    @Override
    public Job update(Job job) {
        return null;
    }
}
