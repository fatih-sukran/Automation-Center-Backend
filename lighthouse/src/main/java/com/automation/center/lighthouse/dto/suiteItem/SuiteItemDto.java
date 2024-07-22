package com.automation.center.lighthouse.dto.suiteItem;

import com.automation.center.lighthouse.dto.metric.MetricDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SuiteItemDto {
    private Long id;
    private String url;
    private String cron;
    private List<MetricDto> metrics = new ArrayList<>();
}
