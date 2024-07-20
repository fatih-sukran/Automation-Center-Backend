package com.automation.center.lighthouse.base;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    List<ENTITY> toEntities(List<DTO> dtos);

    List<DTO> toDtos(List<ENTITY> entities);
}
