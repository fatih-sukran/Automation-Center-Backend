package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.reportItem.AddReportItemDto;
import com.automation.center.lighthouse.dto.reportItem.ReportItemDto;
import com.automation.center.lighthouse.model.ReportItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MetricMapper.class)
public interface ReportItemMapper extends BaseMapper<ReportItem, ReportItemDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metric.id", source = "metricId")
    @Mapping(target = "report.id", source = "reportId")
    ReportItem toEntity(AddReportItemDto dto);

    @Mapping(target = "report.id", source = "reportId")
    @Override
    ReportItem toEntity(ReportItemDto reportItemDto);

    @Mapping(target = "reportId", source = "report.id")
    @Override
    ReportItemDto toDto(ReportItem reportItem);
}
