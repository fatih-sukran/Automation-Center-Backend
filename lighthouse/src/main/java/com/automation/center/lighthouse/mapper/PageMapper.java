package com.automation.center.lighthouse.mapper;

import com.automation.center.lighthouse.base.BaseMapper;
import com.automation.center.lighthouse.dto.page.AddPageDto;
import com.automation.center.lighthouse.dto.page.PageDto;
import com.automation.center.lighthouse.model.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SuiteMapper.class)
public interface PageMapper extends BaseMapper<Page, PageDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "suite.id", source = "suiteId")
    Page toEntity(AddPageDto addMetricUrDto);

    @Mapping(target = "suite.id", source = "suiteId")
    @Override
    Page toEntity(PageDto pageDto);

    @Mapping(target = "suiteId", source = "suite.id")
    @Override
    PageDto toDto(Page page);
}
