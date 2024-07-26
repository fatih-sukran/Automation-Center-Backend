package com.automation.center.jenkins.controller;

import com.automation.center.jenkins.model.Job;
import com.automation.center.jenkins.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService service;

    @PostMapping
    public ResponseEntity<Job> save(@RequestBody Job job) {
        var savedJob = service.save(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findByName(@PathVariable String name) {
        var job = service.get(name);
        return ResponseEntity.ofNullable(job);
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        var jobs = service.getAll();

        return ResponseEntity.ok(jobs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        service.delete(name);
        return ResponseEntity.ok().build();
    }
}
