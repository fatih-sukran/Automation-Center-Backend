package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.suiteItem.AddSuiteItemDto;
import com.automation.center.lighthouse.dto.suiteItem.SuiteItemDto;
import com.automation.center.lighthouse.model.SuiteItem;
import com.automation.center.lighthouse.service.SuiteItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SuiteItemService.class)
public interface SuiteItemMapper extends BaseMapper<SuiteItem, SuiteItemDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metrics", ignore = true)
        //todo: daha sonra id'i entitiy'e dönüştür
    SuiteItem toEntity(AddSuiteItemDto addMetricUrDto);
}
