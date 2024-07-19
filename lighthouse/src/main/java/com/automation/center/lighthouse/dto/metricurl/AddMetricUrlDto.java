package com.automation.center.lighthouse.dto.metricurl;

import lombok.Data;

@Data
public class AddMetricUrlDto {
    private String url;
    private long metricId;
    private String cron;
}
