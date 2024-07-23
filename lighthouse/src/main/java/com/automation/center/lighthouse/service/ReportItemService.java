package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.ReportItem;
import com.automation.center.lighthouse.repository.ReportItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportItemService extends BaseCrud<ReportItem, Long, ReportItemRepository> {

    public ReportItemService(ReportItemRepository repository) {
        super(repository);
    }
}
