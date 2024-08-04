package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.SuiteUrl;
import com.automation.center.lighthouse.repository.SuiteItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SuiteItemService extends BaseCrud<SuiteUrl, Long, SuiteItemRepository> {

    public SuiteItemService(SuiteItemRepository repository) {
        super(repository);
    }


}
