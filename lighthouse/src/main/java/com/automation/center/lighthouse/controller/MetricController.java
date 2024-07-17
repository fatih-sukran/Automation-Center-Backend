package com.automation.center.lighthouse.controller;


import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.mapper.MetricMapper;
import com.automation.center.lighthouse.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metric")
@RequiredArgsConstructor
public class MetricController {
    private final MetricMapper mapper;
    private final MetricService service;

    @PostMapping
    public MetricDto save(@RequestBody AddMetricDto testClass) {
        var metric = mapper.toMetric(testClass);
        var savedMetric = service.save(metric);

        return mapper.toMetricDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricDto> findById(@PathVariable Long id) {
        var optionalMetric = service.findById(id);
        if (optionalMetric.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var metricDto = mapper.toMetricDto(optionalMetric.get());
        return ResponseEntity.ok(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<MetricDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
