package com.fatih.automation.restapi.repositories;

import com.fatih.automation.common.model.TestClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestClassRepository extends JpaRepository<TestClass, Long> {
    boolean existsByPath(String path);
}
