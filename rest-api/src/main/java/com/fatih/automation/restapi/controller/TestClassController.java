package com.fatih.automation.restapi.controller;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.restapi.services.TestClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/testclass")
@RequiredArgsConstructor
public class TestClassController {
    private final TestClassService service;

    @PostMapping
    public TestClass save(@RequestBody TestClass testClass) {

        return service.save(testClass);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TestClass>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestClass>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
