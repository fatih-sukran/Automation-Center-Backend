package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.report.ReportDto;
import com.automation.center.lighthouse.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ResultMapper.class, PageMapper.class})
public interface ReportMapper extends BaseMapper<Report, ReportDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "results", ignore = true)
    @Mapping(target = "suite.id", source = "suiteId")
    Report toEntity(AddReportDto dto);

    @Mapping(target = "suite.id", source = "suiteId")
    @Override
    Report toEntity(ReportDto reportDto);

    @Mapping(target = "suiteName", source = "suite.name")
    @Mapping(target = "suiteId", source = "suite.id")
    @Override
    ReportDto toDto(Report report);
}
