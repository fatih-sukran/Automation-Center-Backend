package com.automation.center.lighthouse.dto.resultHistory;

import lombok.Data;

@Data
public class AddResultHistoryDto {
    private Long testSuiteId;
    private Long metricUrlId;
    private String value;
}
