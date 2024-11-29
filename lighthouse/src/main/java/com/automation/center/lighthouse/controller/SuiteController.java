package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.base.ProgrammaticallyScheduledTasks;
import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.suite.AddSuiteDto;
import com.automation.center.lighthouse.dto.suite.SuiteDto;
import com.automation.center.lighthouse.service.SuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/test-suite", name = "Test Suite Controller")
@RequiredArgsConstructor
public class SuiteController {
    private final SuiteService service;
    private final ProgrammaticallyScheduledTasks scheduledTasks;

    @PostMapping
    public ResponseEntity<BaseResponseWithData<SuiteDto>> save(@RequestBody AddSuiteDto addDto) {
        var savedDto = service.save(addDto);
        var response = new BaseResponseWithData<>(savedDto);
        scheduledTasks.scheduleAllSuites();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseWithData<SuiteDto>> findById(@PathVariable Long id) {
        var suiteDtoOptional = service.findById(id);
        if (suiteDtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = new BaseResponseWithData<>(suiteDtoOptional.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponseWithData<List<SuiteDto>>> findAll() {
        var suiteDtos = service.findAll();
        var response = new BaseResponseWithData<>(suiteDtos);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/metric/{metricId}")
    public ResponseEntity<Void> addMetric(@PathVariable Long id, @PathVariable Long metricId) {
        service.addMetricToSuite(id, metricId);
        scheduledTasks.scheduleAllSuites();

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/metric/{metricId}")
    public ResponseEntity<Void> removeMetric(@PathVariable Long id, @PathVariable Long metricId) {
        service.removeMetricFromSuite(id, metricId);
        scheduledTasks.scheduleAllSuites();

        return ResponseEntity.ok().build();
    }
}

