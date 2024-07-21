package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.UrlProductDto.AddMetricUrlDto;
import com.automation.center.lighthouse.dto.UrlProductDto.MetricUrlDto;
import com.automation.center.lighthouse.model.MetricUrl;
import com.automation.center.lighthouse.service.MetricUrlService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MetricUrlService.class)
public interface MetricUrlMapper extends BaseMapper<MetricUrl, MetricUrlDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrics", ignore = true)
        //todo: daha sonra id'i entitiy'e dönüştür
    MetricUrl toEntity(AddMetricUrlDto addMetricUrDto);
}
