package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.MetricResult;
import com.automation.center.lighthouse.repository.MetricResultRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricResultService extends BaseCrud<MetricResult, Long, MetricResultRepository> {

    public MetricResultService(MetricResultRepository repository) {
        super(repository);
    }
}
