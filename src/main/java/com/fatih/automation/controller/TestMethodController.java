package com.fatih.automation.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class TestMethodController {

    private final TestMethodRepository testMethodRepository;

    @GetMapping("/getTestMethods")
    public String test() {
        StringBuilder result = new StringBuilder();
        for (var testMethod : testMethodRepository.findAll()) {
            result.append(testMethod.getName()).append("<br>");
        }
        return result.toString();
    }

}
