package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.metricResult.AddMetricResultDto;
import com.automation.center.lighthouse.dto.metricResult.MetricResultDto;
import com.automation.center.lighthouse.model.MetricResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetricResultMapper extends BaseMapper<MetricResult, MetricResultDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metric.id", source = "metricId")
    @Mapping(target = "metricUrl.id", source = "metricUrlId")
    @Mapping(target = "testSuite.id", source = "testSuiteId")
    MetricResult toEntity(AddMetricResultDto dto);
}
