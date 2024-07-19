package com.automation.center.lighthouse.controller;


import com.automation.center.lighthouse.dto.metricurl.AddMetricUrlDto;
import com.automation.center.lighthouse.dto.metricurl.MetricUrlDto;
import com.automation.center.lighthouse.mapper.MetricUrlMapper;
import com.automation.center.lighthouse.service.MetricUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metric-url")
@RequiredArgsConstructor
public class MetricUrlController {
    private final MetricUrlMapper mapper;
    private final MetricUrlService service;

    @PostMapping
    public MetricUrlDto save(@RequestBody AddMetricUrlDto addMetricUrlDto) {
        var metric = mapper.toModel(addMetricUrlDto);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricUrlDto> findById(@PathVariable Long id) {
        var optionalMetric = service.findById(id);
        if (optionalMetric.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var metricDto = mapper.toDto(optionalMetric.get());

        return ResponseEntity.ok(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<MetricUrlDto>> findAll() {
        var metrics = service.findAll();
        var metricDtos = mapper.toDto(metrics);

        return ResponseEntity.ok(metricDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
