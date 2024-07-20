package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto;
import com.automation.center.lighthouse.model.TestSuite;
import com.automation.center.lighthouse.service.TestSuiteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TestSuiteService.class)
public interface TestSuiteMapper extends BaseMapper<TestSuite, TestSuiteDto> {
    @Mapping(target = "resultHistories", ignore = true)
    @Mapping(target = "id", ignore = true)
    TestSuite toEntity(AddTestSuiteDto addTestSuiteDto);
}
