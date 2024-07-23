package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.report.ReportDto;
import com.automation.center.lighthouse.mapper.ReportMapper;
import com.automation.center.lighthouse.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/report", name = "Report Controller")
@RequiredArgsConstructor
public class ReportController {
    private final ReportMapper mapper;
    private final ReportService service;

    @PostMapping
    public ReportDto save(@RequestBody AddReportDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = service.save(entity);

        return mapper.toDto(savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        var dto = mapper.toDto(entity);

        return ResponseEntity.ofNullable(dto);
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> findAll() {
        var entities = service.findAll();
        var dtos = mapper.toDtos(entities);

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}