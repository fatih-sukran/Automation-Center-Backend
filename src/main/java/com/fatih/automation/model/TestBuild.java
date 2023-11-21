package com.fatih.automation.model;

import java.util.Map;

public record TestBuild(
        long id,
        TestMethod testMethod,
        Map<String, Object> parameters
) {
}
