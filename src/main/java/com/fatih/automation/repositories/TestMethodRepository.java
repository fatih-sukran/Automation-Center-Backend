package com.fatih.automation.repositories;

import com.fatih.automation.model.TestMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMethodRepository extends JpaRepository<TestMethod, Long> {
    boolean existsByNameAndTestClassId(String name, Long testClassId);
}
