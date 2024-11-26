package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.result.AddResultDto;
import com.automation.center.lighthouse.dto.result.ResultDto;
import com.automation.center.lighthouse.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MetricMapper.class, PageMapper.class})
public interface ResultMapper extends BaseMapper<Result, ResultDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "page.id", source = "pageId")
    @Mapping(target = "metric.id", source = "metricId")
    @Mapping(target = "report.id", source = "reportId")
    Result toEntity(AddResultDto dto);

    @Mapping(target = "report.id", source = "reportId")
    @Override
    Result toEntity(ResultDto resultDto);

    @Mapping(target = "reportId", source = "report.id")
    @Override
    ResultDto toDto(Result result);
}
