package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.service.MetricService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper extends BaseMapper<Metric, MetricDto> {

    Metric toEntity(AddMetricDto metricDto);

    @Override
    Metric toEntity(MetricDto metricDto);

    @Override
    MetricDto toDto(Metric metric);

    default List<MetricDto> toMetrics(List<Long> metricIds, @Context MetricService metricService) {
        return metricIds.stream().map(metricId -> metricService.findById(metricId).orElse(null)).toList();
    }
}
