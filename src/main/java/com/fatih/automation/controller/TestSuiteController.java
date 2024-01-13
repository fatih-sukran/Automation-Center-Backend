package com.fatih.automation.controller;

import com.fatih.automation.model.TestSuite;
import com.fatih.automation.services.TestSuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/testsuite")
@RequiredArgsConstructor
public class TestSuiteController {
    private final TestSuiteService service;

    @PostMapping
    public TestSuite save(@RequestBody TestSuite testSuite) {
        return service.save(testSuite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TestSuite>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestSuite>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
