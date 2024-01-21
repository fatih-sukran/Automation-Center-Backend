package com.fatih.automation.restapi.repositories.build;

import com.fatih.automation.common.model.build.ClassBuild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassBuildRepository extends JpaRepository<ClassBuild, Long> {
}
