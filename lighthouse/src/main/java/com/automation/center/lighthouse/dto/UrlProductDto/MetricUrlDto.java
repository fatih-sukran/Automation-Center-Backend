package com.automation.center.lighthouse.dto.UrlProductDto;

import com.automation.center.lighthouse.dto.metric.MetricDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetricUrlDto {
    private Long id;
    private String url;
    private List<MetricDto> metrics = new ArrayList<>();
    private String cron;
}
