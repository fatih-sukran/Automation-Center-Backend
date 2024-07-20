package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.metricurl.AddMetricUrlDto;
import com.automation.center.lighthouse.dto.metricurl.MetricUrlDto;
import com.automation.center.lighthouse.model.MetricUrl;
import com.automation.center.lighthouse.service.MetricUrlService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MetricUrlService.class)
public interface MetricUrlMapper extends BaseMapper<MetricUrl, MetricUrlDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metric.id", source = "metricId")
    MetricUrl toEntity(AddMetricUrlDto addMetricUrlDto);
}
