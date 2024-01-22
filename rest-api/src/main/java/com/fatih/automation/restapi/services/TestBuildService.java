package com.fatih.automation.restapi.services;

import com.fatih.automation.common.model.TestBuild;
import com.fatih.automation.jenkins.utils.JenkinsUtil;
import com.fatih.automation.restapi.core.BaseCrud;
import com.fatih.automation.restapi.repositories.TestBuildRepository;
import org.springframework.stereotype.Service;


@Service
public class TestBuildService extends BaseCrud<TestBuild, Long, TestBuildRepository> {


    public TestBuildService(TestBuildRepository repository) {
        super(repository);
    }

    public void updateStatus(TestBuild testBuild) {
        var updatedTestBuild = JenkinsUtil.updateBuildStatus(testBuild);
        repository.save(updatedTestBuild);
    }
}
