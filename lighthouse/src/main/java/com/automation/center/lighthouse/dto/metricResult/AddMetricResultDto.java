package com.automation.center.lighthouse.dto.metricResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddMetricResultDto {
    @JsonProperty("metric_id")
    private Long metricId;
    @JsonProperty("metric_url_id")
    private Long metricUrlId;
    @JsonProperty("test_suite_id")
    private Long testSuiteId;
    private String value;
}
