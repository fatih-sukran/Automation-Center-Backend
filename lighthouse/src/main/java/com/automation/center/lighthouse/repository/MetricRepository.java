package com.automation.center.lighthouse.repository;

import com.automation.center.lighthouse.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
