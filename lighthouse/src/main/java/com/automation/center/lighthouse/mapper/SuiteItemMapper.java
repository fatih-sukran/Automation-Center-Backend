package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.suiteItem.AddSuiteUrlDto;
import com.automation.center.lighthouse.dto.suiteItem.SuiteUrlDto;
import com.automation.center.lighthouse.model.SuiteUrl;
import com.automation.center.lighthouse.service.SuiteItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SuiteItemService.class, MetricMapper.class})
public interface SuiteItemMapper extends BaseMapper<SuiteUrl, SuiteUrlDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "testSuite.id", source = "suiteId")
        //todo: daha sonra id'i entitiy'e dönüştür
    SuiteUrl toEntity(AddSuiteUrlDto addMetricUrDto);
}
