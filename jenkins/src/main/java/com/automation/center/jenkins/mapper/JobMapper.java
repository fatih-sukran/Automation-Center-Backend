package com.automation.center.jenkins.mapper;

import com.automation.center.jenkins.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface JobMapper {
    @Mapping(target = "description", ignore = true)
    Job map(com.offbytwo.jenkins.model.Job job);

    @Mapping(target = "client", ignore = true)
    com.offbytwo.jenkins.model.Job map(Job job);
}
