package com.automation.center.lighthouse.dto.metricResult;

import com.automation.center.lighthouse.dto.UrlProductDto.MetricUrlDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetricResultDto {
    private Long id;
    private MetricDto metric;
    @JsonProperty("metric_url")
    private MetricUrlDto metricUrl;
    private String value;
}
