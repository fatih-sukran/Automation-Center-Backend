package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.mapper.TestSuiteMapper;
import com.automation.center.lighthouse.service.TestSuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-suite")
@RequiredArgsConstructor
public class TestSuiteController {
    private final TestSuiteMapper mapper;
    private final TestSuiteService service;

    @PostMapping
    public TestSuiteDto save(@RequestBody AddTestSuiteDto addMetricUrlDto) {
        var metric = mapper.toEntity(addMetricUrlDto);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestSuiteDto> findById(@PathVariable Long id) {
        var metricUrl = service.findById(id);
        var metricDto = mapper.toDto(metricUrl);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<TestSuiteDto>> findAll() {
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

