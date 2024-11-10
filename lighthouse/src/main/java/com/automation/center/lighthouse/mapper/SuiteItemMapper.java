package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.suiteUrl.AddSuiteUrlDto;
import com.automation.center.lighthouse.dto.suiteUrl.SuiteUrlDto;
import com.automation.center.lighthouse.model.SuiteUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuiteItemMapper extends BaseMapper<SuiteUrl, SuiteUrlDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "testSuite.id", source = "suiteId")
    SuiteUrl toEntity(AddSuiteUrlDto addMetricUrDto);

    @Mapping(target = "suiteId", source = "testSuite.id")
    SuiteUrlDto toDto(SuiteUrl suiteUrl);
}
