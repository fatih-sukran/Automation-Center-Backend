package com.automation.center.lighthouse.repository;

import com.automation.center.lighthouse.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
