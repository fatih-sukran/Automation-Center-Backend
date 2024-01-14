package com.fatih.automation.restapi.controller;

import com.fatih.automation.restapi.model.TestSuite;
import com.fatih.automation.restapi.services.TestSuiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/testsuite")
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

    @PostMapping("/{id}/testmethod")
    public TestSuite addTestMethod(@PathVariable Long id, @RequestBody List<Long> testMethodIds) {
        return service.addTestMethods(id, testMethodIds);
    }

    @PostMapping("/{id}/testclass")
    public TestSuite addTestClasses(@PathVariable Long id, @RequestBody List<Long> testClassIds) {
        return service.addTestClasses(id, testClassIds);
    }
}
