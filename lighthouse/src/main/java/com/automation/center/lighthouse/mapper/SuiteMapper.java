package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.suite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.suite.SuiteDto;
import com.automation.center.lighthouse.model.Suite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {MetricMapper.class, PageMapper.class})
public interface SuiteMapper extends BaseMapper<Suite, SuiteDto> {
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "pages", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrics", ignore = true)
        //todo: fix it
    Suite toEntity(AddTestSuiteDto addTestSuiteDto);

    @Override
    Suite toEntity(SuiteDto suiteDto);

    @Override
    SuiteDto toDto(Suite suite);
}
