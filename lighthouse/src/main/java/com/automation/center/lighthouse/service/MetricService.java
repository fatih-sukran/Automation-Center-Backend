package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.mapper.MetricMapper;
import com.automation.center.lighthouse.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final MetricRepository repository;
    private final MetricMapper mapper;

    public MetricDto save(AddMetricDto t) {
        var entity = mapper.toEntity(t);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public MetricDto findById(Long id) {
        var entity = repository.findById(id).orElseThrow();
        return mapper.toDto(entity);
    }

    public List<MetricDto> findAll() {
        var entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    public void delete(MetricDto t) {
        var entity = mapper.toEntity(t);
        repository.delete(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
