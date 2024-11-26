package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.suite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.suite.SuiteDto;
import com.automation.center.lighthouse.mapper.SuiteMapper;
import com.automation.center.lighthouse.service.MetricService;
import com.automation.center.lighthouse.service.SuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/test-suite", name = "Test Suite Controller")
@RequiredArgsConstructor
public class SuiteController {
    private final SuiteMapper mapper;
    private final SuiteService service;

    private final MetricService metricService;

    @PostMapping
    public ResponseEntity<BaseResponseWithData<SuiteDto>> save(@RequestBody AddTestSuiteDto addDto) {
//        var entity = mapper.toEntity(addDto);
//        addDto.getMetrics().forEach(metricId -> entity.getMetrics().add(new Metric(metricId)));
//        var savedEntity = service.save(entity);
//
//        return mapper.toDto(savedEntity);

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuiteDto> findById(@PathVariable Long id) {
//        var entity = service.findById(id);
//        var dto = mapper.toDto(entity);
//
//        return ResponseEntity.ofNullable(dto);

        return null;
    }

    @GetMapping
    public ResponseEntity<List<SuiteDto>> findAll() {
//        var entities = service.findAll();
//        var dtos = mapper.toDtos(entities);
//
//        return ResponseEntity.ok(dtos);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.deleteById(id);
//        return ResponseEntity.ok().build();
        return null;
    }

    @PostMapping("/{id}/metric/{metricId}")
    public SuiteDto addMetric(@PathVariable Long id, @PathVariable Long metricId) {
//        var suite = service.findById(id);
//        var metric = metricService.findById(metricId);
//
////        suite.getMetrics().add(metric); todo: açılacak
//        var savedData = service.save(suite);
//        return mapper.toDto(savedData);
        return null;
    }
}

