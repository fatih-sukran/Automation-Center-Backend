package com.fatih.automation.restapi.repositories;

import com.fatih.automation.common.model.TestBuild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBuildRepository extends JpaRepository<TestBuild, Long> {
}
