package com.fatih.automation.repositories;

import com.fatih.automation.model.TestClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestClassRepository extends JpaRepository<TestClass, Long> {
    boolean existsByPath(String path);
}
