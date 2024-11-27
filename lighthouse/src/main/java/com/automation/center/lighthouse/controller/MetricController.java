package com.automation.center.lighthouse.controller;


import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metric")
@RequiredArgsConstructor
public class MetricController {
    private final MetricService service;

    @PostMapping
    public ResponseEntity<BaseResponseWithData<MetricDto>> save(@RequestBody AddMetricDto testClass) {
        var savedMetric = service.save(testClass);
        var response = new BaseResponseWithData<>(savedMetric);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseWithData<MetricDto>> findById(@PathVariable Long id) {
        var metric = service.findById(id);
        if (metric.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new BaseResponseWithData<>(metric.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponseWithData<List<MetricDto>>> findAll() {
        var metrics = service.findAll();
        var response = new BaseResponseWithData<>(metrics);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
