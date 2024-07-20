package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.resultHistory.AddResultHistoryDto;
import com.automation.center.lighthouse.dto.resultHistory.ResultHistoryDto;
import com.automation.center.lighthouse.model.ResultHistory;
import com.automation.center.lighthouse.service.ResultHistoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ResultHistoryService.class)
public interface ResultHistoryMapper extends BaseMapper<ResultHistory, ResultHistoryDto> {
    @Mapping(target = "url.id", source = "metricUrlId")
    @Mapping(target = "testSuite.id", source = "testSuiteId")
    @Mapping(target = "id", ignore = true)
    ResultHistory toEntity(AddResultHistoryDto addResultHistoryDto);
}
