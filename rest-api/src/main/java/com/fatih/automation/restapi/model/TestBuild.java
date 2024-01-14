package com.fatih.automation.restapi.model;

import java.util.Map;
import java.util.UUID;

public record TestBuild(
        UUID id,
        TestMethod testMethod,
        Map<String, Object> parameters
) {
}
