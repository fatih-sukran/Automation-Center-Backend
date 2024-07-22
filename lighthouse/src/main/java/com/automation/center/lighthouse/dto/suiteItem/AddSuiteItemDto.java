package com.automation.center.lighthouse.dto.suiteItem;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddSuiteItemDto {
    private String url;
    private String cron;
    private List<Long> metricIds = new ArrayList<>();
}
