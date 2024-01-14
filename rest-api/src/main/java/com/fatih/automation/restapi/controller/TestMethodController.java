package com.fatih.automation.restapi.controller;

import com.fatih.automation.restapi.model.TestMethod;
import com.fatih.automation.restapi.services.TestMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/testmethod")
@RequiredArgsConstructor
public class TestMethodController {

    private final TestMethodService service;
    @PostMapping
    public TestMethod save(@RequestBody TestMethod testMethod) {
        return service.save(testMethod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TestMethod>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestMethod>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
