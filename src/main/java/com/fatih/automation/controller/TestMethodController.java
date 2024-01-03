package com.fatih.automation.controller;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.SneakyThrows;
import net.sf.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/testmethods")
public class TestMethodController {



    @GetMapping
    public List<TestMethod> getAll() {
        return List.of();
    }

    @PostMapping
    public ResponseEntity<TestMethod> add(@RequestBody TestMethod testMethod) {
        return ResponseEntity.of(Optional.of(testMethod));
    }
}
