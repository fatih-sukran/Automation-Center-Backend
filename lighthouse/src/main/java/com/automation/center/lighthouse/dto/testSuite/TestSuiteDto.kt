package com.automation.center.lighthouse.dto.testSuite;

import com.automation.center.lighthouse.dto.metricResult.MetricResultDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TestSuiteDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private String description;
    private String cron;
    private List<MetricResultDto> metricResults = new ArrayList<>();
}
