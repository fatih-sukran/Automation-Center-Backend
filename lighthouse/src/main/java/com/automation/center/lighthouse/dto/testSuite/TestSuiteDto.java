package com.automation.center.lighthouse.dto.testSuite;

import com.automation.center.lighthouse.dto.resultHistory.ResultHistoryDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestSuiteDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<ResultHistoryDto> resultHistories;
}
