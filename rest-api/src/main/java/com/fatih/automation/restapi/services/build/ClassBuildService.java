package com.fatih.automation.restapi.services.build;

import com.fatih.automation.common.model.build.ClassBuild;
import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.repositories.build.ClassBuildRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassBuildService extends BaseCrud<ClassBuild, Long, ClassBuildRepository> {
    public ClassBuildService(ClassBuildRepository repository) {
        super(repository);
    }
}
