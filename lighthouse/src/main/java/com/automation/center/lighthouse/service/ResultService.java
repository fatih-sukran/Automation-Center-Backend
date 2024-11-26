package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.result.AddResultDto;
import com.automation.center.lighthouse.dto.result.ResultDto;
import com.automation.center.lighthouse.mapper.ResultMapper;
import com.automation.center.lighthouse.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ResultService {
    private final ResultMapper mapper;
    private final ResultRepository repository;

    public List<ResultDto> findAll() {
        var entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    public Optional<ResultDto> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    public ResultDto save(AddResultDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(ResultDto dto) {
        var id = Objects.requireNonNull(dto.getId());
        repository.deleteById(id);
    }
}
