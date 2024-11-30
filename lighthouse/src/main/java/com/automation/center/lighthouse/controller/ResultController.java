package com.automation.center.lighthouse.controller;

import com.automation.center.lighthouse.dto.BaseResponseWithData;
import com.automation.center.lighthouse.dto.result.AddResultDto;
import com.automation.center.lighthouse.dto.result.ResultDto;
import com.automation.center.lighthouse.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService service;

    @PostMapping
    public ResponseEntity<BaseResponseWithData<ResultDto>> save(@RequestBody AddResultDto addResultDto) {
        var savedDto = service.save(addResultDto);
        var response = new BaseResponseWithData<>(savedDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseWithData<ResultDto>> findById(@PathVariable Long id) {
        var resultDtoOptional = service.findById(id);
        if (resultDtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = new BaseResponseWithData<>(resultDtoOptional.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponseWithData<List<ResultDto>>> findAll() {
        var resultDtos = service.findAll();
        var response = new BaseResponseWithData<>(resultDtos);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

