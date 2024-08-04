package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.model.TestSuite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MetricMapper.class)
public interface TestSuiteMapper extends BaseMapper<TestSuite, TestSuiteDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrics", ignore = true)
    @Mapping(target = "urls", ignore = true)
    TestSuite toEntity(AddTestSuiteDto addTestSuiteDto);
}
