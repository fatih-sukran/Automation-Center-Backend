package com.automation.center.lighthouse.controller;


import com.automation.center.lighthouse.dto.UrlProductDto.AddMetricUrlDto;
import com.automation.center.lighthouse.dto.UrlProductDto.MetricUrlDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.mapper.MetricMapper;
import com.automation.center.lighthouse.mapper.MetricUrlMapper;
import com.automation.center.lighthouse.service.MetricService;
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

    private final MetricMapper metricMapper;
    private final MetricService metricService;

    @PostMapping(name = "Add Metric Url")
    public MetricUrlDto save(@RequestBody AddMetricUrlDto addMetricUrDto) {
        var metric = mapper.toEntity(addMetricUrDto);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping(value = "/{id}", name = "Find Metric Url")
    public ResponseEntity<MetricUrlDto> findById(@PathVariable Long id) {
        var metricUrl = service.findById(id);
        var metricDto = mapper.toDto(metricUrl);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping(name = "Find All Metric Urls")
    public ResponseEntity<List<MetricUrlDto>> findAll() {
        var metrics = service.findAll();
        var metricDtos = mapper.toDtos(metrics);

        return ResponseEntity.ok(metricDtos);
    }

    @DeleteMapping(value = "/{id}", name = "Delete Metric Url")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/metric", name = "Add Metric to Metric Url")
    public MetricUrlDto addMetric(@PathVariable Long id, @RequestBody MetricDto metricDto) {
        // Find or create metric
        var metric = metricService.findById(metricDto.id());
        if (metric == null) {
            metric = metricMapper.toEntity(metricDto);
            metricService.save(metric);
        }

        // if not found return null else add metric
        var metricUrl = service.findById(id);
        if (metricUrl == null) {
            return null;
        } else {
            metricUrl.getMetrics().add(metric);
            var savedMetric = service.save(metricUrl);
            return mapper.toDto(savedMetric);
        }
    }

    @DeleteMapping(value = "/{id}/metric/{metricId}", name = "Remove Metric from Metric Url")
    public MetricUrlDto removeMetric(@PathVariable Long id, @PathVariable Long metricId) {
        var metricUrl = service.findById(id);
        if (metricUrl == null) {
            return null;
        } else {
            metricUrl.getMetrics().removeIf(metric -> metric.getId().equals(metricId));
            return mapper.toDto(metricUrl);
        }
    }
}
