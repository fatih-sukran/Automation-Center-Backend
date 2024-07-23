package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.report.ReportDto;
import com.automation.center.lighthouse.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReportItemMapper.class)
public interface ReportMapper extends BaseMapper<Report, ReportDto> {
    @Mapping(target = "reportItems", ignore = true)
    @Mapping(target = "testSuite", ignore = true)
    @Mapping(target = "id", ignore = true)
    Report toEntity(AddReportDto dto);
}
