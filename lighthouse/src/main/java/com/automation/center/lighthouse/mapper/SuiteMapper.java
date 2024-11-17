package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.model.Suite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MetricMapper.class, PageMapper.class})
public interface SuiteMapper extends BaseMapper<Suite, TestSuiteDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrics", ignore = true)
    @Mapping(target = "urls", ignore = true)
    Suite toEntity(AddTestSuiteDto addTestSuiteDto);

    @Override
    Suite toEntity(TestSuiteDto testSuiteDto);
}
