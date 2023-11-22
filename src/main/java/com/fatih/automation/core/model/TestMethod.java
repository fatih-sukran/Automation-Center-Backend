package com.fatih.automation.core.model;

import java.util.List;

public record TestMethod(
        long id,
        String name,
        String description,
        boolean isActive,
        boolean isDataProvider,
        List<String> parameters
) {
    TestBuild run() {
        // TODO: run test
        return null;
    }
}
