package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.mapper.TestSuiteMapper;
import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.service.MetricService;
import com.automation.center.lighthouse.service.TestSuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/test-suite", name = "Test Suite Controller")
@RequiredArgsConstructor
public class TestSuiteController {
    private final TestSuiteMapper mapper;
    private final TestSuiteService service;

    private final MetricService metricService;

    @PostMapping
    public TestSuiteDto save(@RequestBody AddTestSuiteDto addDto) {
        var entity = mapper.toEntity(addDto);
        addDto.getMetrics().forEach(metricId -> entity.getMetrics().add(new Metric(metricId)));
        var savedEntity = service.save(entity);

        return mapper.toDto(savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestSuiteDto> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        var dto = mapper.toDto(entity);

        return ResponseEntity.ofNullable(dto);
    }

    @GetMapping
    public ResponseEntity<List<TestSuiteDto>> findAll() {
        var entities = service.findAll();
        var dtos = mapper.toDtos(entities);

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/metric/{metricId}")
    public TestSuiteDto addMetric(@PathVariable Long id, @PathVariable Long metricId) {
        var suite = service.findById(id);
        var metric = metricService.findById(metricId);

        suite.getMetrics().add(metric);
        var savedData = service.save(suite);
        return mapper.toDto(savedData);
    }
}

