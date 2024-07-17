package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.model.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    @Mapping(target = "id", source = "")
    Metric toMetric(AddMetricDto addMetricDto);

    Metric toMetric(MetricDto metricDto);

    AddMetricDto toAddMetricDto(Metric metric);

    MetricDto toMetricDto(Metric metric);
}
