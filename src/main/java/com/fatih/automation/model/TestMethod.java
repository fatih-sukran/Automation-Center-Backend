package com.fatih.automation.model;

import java.util.List;

public record TestMethod(
        long id,
        String name,
        String description,
        boolean isActive,
        boolean isDataProvider,
        List<String> parameters
) {}
