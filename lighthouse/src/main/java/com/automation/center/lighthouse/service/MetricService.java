package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.mapper.MetricMapper;
import com.automation.center.lighthouse.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<MetricDto> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
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
}
