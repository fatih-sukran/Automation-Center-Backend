package com.automation.center.lighthouse.dto.UrlProductDto;

import lombok.Data;

import java.util.List;

@Data
public class AddMetricUrlDto {
    private String url;
    private List<Long> metricIds;
    private String cron;
}
