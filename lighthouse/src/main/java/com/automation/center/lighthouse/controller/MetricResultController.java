package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.metricResult.AddMetricResultDto;
import com.automation.center.lighthouse.dto.metricResult.MetricResultDto;
import com.automation.center.lighthouse.mapper.MetricResultMapper;
import com.automation.center.lighthouse.service.MetricResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metric-result")
@RequiredArgsConstructor
public class MetricResultController {
    private final MetricResultMapper mapper;
    private final MetricResultService service;

    @PostMapping
    public MetricResultDto save(@RequestBody AddMetricResultDto testClass) {
        var metric = mapper.toEntity(testClass);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricResultDto> findById(@PathVariable Long id) {
        var metric = service.findById(id);
        var metricDto = mapper.toDto(metric);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<MetricResultDto>> findAll() {
        var metrics = service.findAll();
        var metricDtos = mapper.toDtos(metrics);

        return ResponseEntity.ok(metricDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

