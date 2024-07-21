package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.metricResult.AddMetricResultDto;
import com.automation.center.lighthouse.dto.metricResult.MetricResultDto;
import com.automation.center.lighthouse.model.MetricResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MetricResultMapper extends BaseMapper<MetricResult, MetricResultDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metric", source = "metricId")
    @Mapping(target = "metricUrl", source = "metricUrlId")
    MetricResult toEntity(AddMetricResultDto dto);
}
