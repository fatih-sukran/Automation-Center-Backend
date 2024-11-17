package com.automation.center.lighthouse.repository;

import com.automation.center.lighthouse.model.Suite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiteRepository extends JpaRepository<Suite, Long> {
}
