package com.automation.center.lighthouse.dto.metricResult;

import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.model.MetricUrl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetricResultDto {
    private Long id;
    private Metric metric;
    @JsonProperty("metric_url")
    private MetricUrl metricUrl;
    private String value;
}
