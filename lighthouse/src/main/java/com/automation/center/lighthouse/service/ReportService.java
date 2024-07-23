package com.automation.center.lighthouse.service;

import com.automation.center.lighthouse.base.BaseCrud;
import com.automation.center.lighthouse.model.Report;
import com.automation.center.lighthouse.repository.ReportRepository;

public class ReportService extends BaseCrud<Report, Long, ReportRepository> {

    public ReportService(ReportRepository repository) {
        super(repository);
    }
}
