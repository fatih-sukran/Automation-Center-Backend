package com.automation.center.lighthouse.repository;

import com.automation.center.lighthouse.model.SuiteUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiteItemRepository extends JpaRepository<SuiteUrl, Long> {
}
