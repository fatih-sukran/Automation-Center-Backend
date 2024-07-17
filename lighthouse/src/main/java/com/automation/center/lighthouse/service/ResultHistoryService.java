package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.ResultHistory;
import com.automation.center.lighthouse.repository.ResultHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ResultHistoryService extends BaseCrud<ResultHistory, Long, ResultHistoryRepository> {

    public ResultHistoryService(ResultHistoryRepository repository) {
        super(repository);
    }
}
