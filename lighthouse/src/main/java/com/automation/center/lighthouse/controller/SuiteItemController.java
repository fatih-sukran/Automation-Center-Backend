package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.suiteItem.AddSuiteUrlDto;
import com.automation.center.lighthouse.dto.suiteItem.SuiteUrlDto;
import com.automation.center.lighthouse.mapper.SuiteItemMapper;
import com.automation.center.lighthouse.service.SuiteItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suite-item")
@RequiredArgsConstructor
public class SuiteItemController {
    private final SuiteItemMapper mapper;
    private final SuiteItemService service;

    @PostMapping(name = "Add Metric Url")
    public SuiteUrlDto save(@RequestBody AddSuiteUrlDto addMetricUrDto) {
        var metric = mapper.toEntity(addMetricUrDto);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping(value = "/{id}", name = "Find Metric Url")
    public ResponseEntity<SuiteUrlDto> findById(@PathVariable Long id) {
        var metricUrl = service.findById(id);
        var metricDto = mapper.toDto(metricUrl);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping(name = "Find All Metric Urls")
    public ResponseEntity<List<SuiteUrlDto>> findAll() {
        var metrics = service.findAll();
        var metricDtos = mapper.toDtos(metrics);

        return ResponseEntity.ok(metricDtos);
    }

    @DeleteMapping(value = "/{id}", name = "Delete Metric Url")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
