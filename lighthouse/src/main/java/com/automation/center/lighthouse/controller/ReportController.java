package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.report.ReportDto;
import com.automation.center.lighthouse.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/report", name = "Report Controller")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService service;

    @PostMapping
    public ResponseEntity<BaseResponseWithData<ReportDto>> save(@RequestBody AddReportDto addDto) {
        var savedEntity = service.save(addDto);
        var response = new BaseResponseWithData<>(savedEntity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseWithData<ReportDto>> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new BaseResponseWithData<>(entity.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponseWithData<List<ReportDto>>> findAll() {
        var entities = service.findAll();
        var response = new BaseResponseWithData<>(entities);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}