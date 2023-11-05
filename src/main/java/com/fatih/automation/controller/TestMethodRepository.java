package com.fatih.automation.controller;

import com.github.javaparser.ast.body.MethodDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMethodRepository extends JpaRepository<TestMethod, Long> {
}
