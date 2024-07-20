package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.resultHistory.AddResultHistoryDto;
import com.automation.center.lighthouse.dto.resultHistory.ResultHistoryDto;
import com.automation.center.lighthouse.mapper.ResultHistoryMapper;
import com.automation.center.lighthouse.service.ResultHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/result-history")
@RequiredArgsConstructor
public class ResultHistoryController {
    private final ResultHistoryMapper mapper;
    private final ResultHistoryService service;

    @PostMapping
    public ResultHistoryDto save(@RequestBody AddResultHistoryDto addMetricUrlDto) {
        var metric = mapper.toEntity(addMetricUrlDto);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultHistoryDto> findById(@PathVariable Long id) {
        var metricUrl = service.findById(id);
        var metricDto = mapper.toDto(metricUrl);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<ResultHistoryDto>> findAll() {
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
