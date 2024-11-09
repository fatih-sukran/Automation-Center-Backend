package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.suiteUrl.AddSuiteUrlDto;
import com.automation.center.lighthouse.dto.suiteUrl.SuiteUrlDto;
import com.automation.center.lighthouse.model.SuiteUrl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuiteItemMapper extends BaseMapper<SuiteUrl, SuiteUrlDto> {
    SuiteUrl toEntity(AddSuiteUrlDto addMetricUrDto);
}
