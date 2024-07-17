package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.repository.MetricRepository;

public class MetricService extends BaseCrud<Metric, Long, MetricRepository> {

    public MetricService(MetricRepository repository) {
        super(repository);
    }
}
