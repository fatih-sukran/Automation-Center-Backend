package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.dto.page.AddPageDto;
import com.automation.center.lighthouse.dto.page.PageDto;
import com.automation.center.lighthouse.mapper.PageMapper;
import com.automation.center.lighthouse.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageMapper mapper;
    private final PageRepository repository;

    public List<PageDto> findAll() {
        var foundDtos = repository.findAll();
        return mapper.toDtos(foundDtos);
    }

    public Optional<PageDto> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDto);
    }

    public PageDto save(AddPageDto addDto) {
        var entity = mapper.toEntity(addDto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(PageDto dto) {
        var entity = mapper.toEntity(dto);
        repository.delete(entity);
    }
}
