package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.page.AddPageDto;
import com.automation.center.lighthouse.dto.page.PageDto;
import com.automation.center.lighthouse.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class PageController {
    private final PageService service;

    @PostMapping(name = "Add Metric Url")
    public ResponseEntity<BaseResponseWithData<PageDto>> save(@RequestBody AddPageDto addPageDto) {
        var savedPage = service.save(addPageDto);
        var response = new BaseResponseWithData<>(savedPage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", name = "Find Metric Url")
    public ResponseEntity<BaseResponseWithData<PageDto>> findById(@PathVariable Long id) {
        var pageDtoOptional = service.findById(id);
        if (pageDtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new BaseResponseWithData<>(pageDtoOptional.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping(name = "Find All Metric Urls")
    public ResponseEntity<BaseResponseWithData<List<PageDto>>> findAll() {
        var pageDtos = service.findAll();
        var response = new BaseResponseWithData<>(pageDtos);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}", name = "Delete Metric Url")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
