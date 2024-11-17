package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.metric.MetricDto;
import com.automation.center.lighthouse.dto.page.AddPageDto;
import com.automation.center.lighthouse.dto.page.PageDto;
import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto;
import com.automation.center.lighthouse.dto.testSuite.SuiteDto;
import com.automation.center.lighthouse.mapper.MetricMapper;
import com.automation.center.lighthouse.mapper.SuiteMapper;
import com.automation.center.lighthouse.repository.SuiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SuiteService {

    private final SuiteMapper mapper;
    private final MetricMapper metricMapper;
    private final PageService pageService;
    private final SuiteRepository repository;

    public List<SuiteDto> findAll() {
        var entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    public Optional<SuiteDto> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    public SuiteDto save(AddTestSuiteDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(SuiteDto dto) {
        var entity = mapper.toEntity(dto);
        repository.delete(entity);
    }

    public void addMetricToSuite(Long id, MetricDto dto) {
        var entity = repository.findById(id);
        if (entity.isPresent()) {
            var suite = entity.get();
            var metric = metricMapper.toEntity(dto);
            suite.getMetrics().add(metric);
            repository.save(suite);
        }
    }

    public void removeMetricFromSuite(Long id, MetricDto metricDto) {
        var entity = repository.findById(id);
        if (entity.isPresent()) {
            var suite = entity.get();
            suite.getMetrics().removeIf(metric -> Objects.equals(metric.getId(), metricDto.getId()));
            repository.save(suite);
        }
    }

    public void addPageToSuite(AddPageDto pageDto) {
        pageService.save(pageDto);
    }

    public void removePageFromSuite(PageDto pageDto) {
        pageService.delete(pageDto);
    }
}
