package com.fatih.automation.restapi.repositories;

import com.fatih.automation.common.model.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSuiteRepository extends JpaRepository<TestSuite, Long> {
}
