package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.reportItem.AddReportItemDto;
import com.automation.center.lighthouse.dto.reportItem.ReportItemDto;
import com.automation.center.lighthouse.mapper.ReportItemMapper;
import com.automation.center.lighthouse.service.ReportItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/report-item")
@RequiredArgsConstructor
public class ReportItemController {
    private final ReportItemMapper mapper;
    private final ReportItemService service;

    @PostMapping
    public ReportItemDto save(@RequestBody AddReportItemDto testClass) {
        var metric = mapper.toEntity(testClass);
        var savedMetric = service.save(metric);

        return mapper.toDto(savedMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportItemDto> findById(@PathVariable Long id) {
        var metric = service.findById(id);
        var metricDto = mapper.toDto(metric);

        return ResponseEntity.ofNullable(metricDto);
    }

    @GetMapping
    public ResponseEntity<List<ReportItemDto>> findAll() {
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

