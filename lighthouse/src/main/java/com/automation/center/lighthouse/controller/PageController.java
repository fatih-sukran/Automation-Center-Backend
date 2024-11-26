package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.mapper.PageMapper;
import com.automation.center.lighthouse.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class PageController {
    private final PageMapper mapper;
    private final PageService service;

//    @PostMapping(name = "Add Metric Url")
//    public PageDto save(@RequestBody AddSuiteUrlDto addMetricUrDto) {
//        var metric = mapper.toEntity(addMetricUrDto);
//        var savedMetric = service.save(metric);
//
//        return mapper.toDto(savedMetric);
//    }
//
//    @GetMapping(value = "/{id}", name = "Find Metric Url")
//    public ResponseEntity<PageDto> findById(@PathVariable Long id) {
//        var metricUrl = service.findById(id);
//        var metricDto = mapper.toDto(metricUrl);
//
//        return ResponseEntity.ofNullable(metricDto);
//    }
//
//    @GetMapping(name = "Find All Metric Urls")
//    public ResponseEntity<List<PageDto>> findAll() {
//        var metrics = service.findAll();
//        var metricDtos = mapper.toDtos(metrics);
//
//        return ResponseEntity.ok(metricDtos);
//    }
//
//    @DeleteMapping(value = "/{id}", name = "Delete Metric Url")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        service.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}
