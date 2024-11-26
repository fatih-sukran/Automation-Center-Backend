package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.mapper.ResultMapper;
import com.automation.center.lighthouse.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/report-item")
@RequiredArgsConstructor
public class ResultController {
    private final ResultMapper mapper;
    private final ResultService service;

//    @PostMapping
//    public ResultDto save(@RequestBody AddResultDto testClass) {
//        var metric = mapper.toEntity(testClass);
//        var savedMetric = service.save(metric);
//
//        return mapper.toDto(savedMetric);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
//        var metric = service.findById(id);
//        var metricDto = mapper.toDto(metric);
//
//        return ResponseEntity.ofNullable(metricDto);
//    }

//    @GetMapping
//    public ResponseEntity<List<ResultDto>> findAll() {
//        var metrics = service.findAll();
//        var metricDtos = mapper.toDtos(metrics);
//
//        return ResponseEntity.ok(metricDtos);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}

