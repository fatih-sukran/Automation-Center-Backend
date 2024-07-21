package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.mapper.TestSuiteMapper;
import com.automation.center.lighthouse.service.TestSuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/test-suite", name = "Test Suite Controller")
@RequiredArgsConstructor
public class TestSuiteController {
    private final TestSuiteMapper mapper;
    private final TestSuiteService service;

    @PostMapping
    public TestSuiteDto save(@RequestBody AddTestSuiteDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = service.save(entity);

        return mapper.toDto(savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestSuiteDto> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        var dto = mapper.toDto(entity);

        return ResponseEntity.ofNullable(dto);
    }

    @GetMapping
    public ResponseEntity<List<TestSuiteDto>> findAll() {
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

