package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.model.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetricMapper extends BaseMapper<Metric, MetricDto> {
    @Mapping(target = "metricUrls", ignore = true)
    @Mapping(target = "id", ignore = true)
    Metric toEntity(AddMetricDto metricDto);
}
