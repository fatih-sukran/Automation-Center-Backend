package com.fatih.automation.jenkins.runner;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.common.model.TestMethod;
import com.fatih.automation.common.model.TestSuite;
import com.fatih.automation.jenkins.model.SuiteBuild;
import lombok.Getter;
import org.assertj.core.util.Sets;

import java.util.Set;

@Getter
public abstract class TestRunner {
    private final Set<TestMethod> testMethods = Sets.set();
    private final Set<TestClass> testClasses = Sets.set();
    private final Set<TestSuite> testSuites = Sets.set();

    public abstract SuiteBuild run();
}
