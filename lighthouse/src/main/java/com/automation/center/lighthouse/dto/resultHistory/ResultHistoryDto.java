package com.automation.center.lighthouse.dto.resultHistory;

import com.automation.center.lighthouse.dto.metricurl.MetricUrlDto;
import lombok.Data;

@Data
public class ResultHistoryDto {
    private Long id;
    private Long testSuiteId;
    private MetricUrlDto url;
    private String value;
}
