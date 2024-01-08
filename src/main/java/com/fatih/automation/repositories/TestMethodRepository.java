package com.fatih.automation.repositories;

import com.fatih.automation.model.TestMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMethodRepository extends JpaRepository<TestMethod, Long> {

    boolean existsByNameAndClassName(String name, String className);
}
