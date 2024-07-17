package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.MetricUrl;
import com.automation.center.lighthouse.repository.MetricUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricUrlService extends BaseCrud<MetricUrl, Long, MetricUrlRepository> {

    public MetricUrlService(MetricUrlRepository repository) {
        super(repository);
    }
}
