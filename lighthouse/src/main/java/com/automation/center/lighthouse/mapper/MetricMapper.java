package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.model.Metric;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    Metric toMetrics(AddMetricDto source);

    Metric toMetrics(MetricDto source);

    AddMetricDto toAddMetricDto(Metric source);

    MetricDto toMetricDto(Metric source);

    List<Metric> toMetricsFromAddMetrics(List<AddMetricDto> source);

    List<Metric> toMetricsFromMetricDtos(List<MetricDto> source);

    List<AddMetricDto> toAddMetricDtos(List<Metric> source);

    List<MetricDto> toMetricDtos(List<Metric> source);
}
