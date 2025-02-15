package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.report.ReportDto;
import com.automation.center.lighthouse.dto.result.AddResultDto;
import com.automation.center.lighthouse.dto.result.ResultDto;
import com.automation.center.lighthouse.mapper.ReportMapper;
import com.automation.center.lighthouse.mapper.ResultMapper;
import com.automation.center.lighthouse.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportMapper mapper;
    private final ReportRepository repository;

    private final ResultMapper resultMapper;
    private final ResultService resultService;

    public List<ReportDto> findAll() {
        var entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    public Optional<ReportDto> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    public ReportDto save(AddReportDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(ReportDto dto) {
        var id = Objects.requireNonNull(dto.getId());
        repository.deleteById(id);
    }

    public ResultDto addResult(AddResultDto addDto) {
        return resultService.save(addDto);
    }

    public void removeResult(ResultDto resultDto) {
        resultService.delete(resultDto);
    }
}
