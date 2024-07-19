package com.automation.center.lighthouse.dto.metricurl;

import com.automation.center.lighthouse.model.Metric;
import lombok.Data;

@Data
public class MetricUrlDto {
    private String url;
    private Metric metric;
    private String cron;
}
