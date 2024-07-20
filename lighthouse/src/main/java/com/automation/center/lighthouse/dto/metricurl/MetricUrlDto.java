package com.automation.center.lighthouse.dto.metricurl;

import com.automation.center.lighthouse.dto.metric.MetricDto;
import lombok.Data;

@Data
public class MetricUrlDto {
    private String url;
    private MetricDto metric;
    private String cron;
}
