package com.automation.center.lighthouse.dto.testSuite;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTestSuiteDto {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
