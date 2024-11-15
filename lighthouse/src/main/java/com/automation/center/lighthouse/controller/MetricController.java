package com.automation.center.lighthouse.controller;


import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metric")
@RequiredArgsConstructor
public class MetricController {
    private final MetricService service;

    @PostMapping
    public ResponseEntity<MetricDto> save(@RequestBody AddMetricDto testClass) {
        var savedMetric = service.save(testClass);
        return new ResponseEntity<>(savedMetric, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricDto> findById(@PathVariable Long id) {
        var metric = service.findById(id);
        return ResponseEntity.ofNullable(metric);
    }

    @GetMapping
    public ResponseEntity<List<MetricDto>> findAll() {
        var metrics = service.findAll();
        return ResponseEntity.ok(metrics);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
