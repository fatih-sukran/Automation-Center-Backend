package com.automation.center.lighthouse.base;

import org.mapstruct.Mapping;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {
    @Mapping(target = "id", ignore = true)
    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);

    default List<ENTITY> toEntities(List<DTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    default List<DTO> toDtos(List<ENTITY> entities) {
        return entities.stream().map(this::toDto).toList();
    }
}
