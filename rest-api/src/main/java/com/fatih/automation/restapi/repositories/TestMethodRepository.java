package com.fatih.automation.restapi.repositories;

import com.fatih.automation.restapi.model.TestMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMethodRepository extends JpaRepository<TestMethod, Long> {
    boolean existsByNameAndTestClassId(String name, Long testClassId);
}
