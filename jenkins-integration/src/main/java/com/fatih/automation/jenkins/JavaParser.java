package com.fatih.automation.jenkins;

import com.fatih.automation.common.model.TestClass;

import java.io.File;
import java.util.List;

public interface JavaParser {

    boolean isJavaFile(File file);

    boolean hasTestClass(File file);

    List<TestClass> findTestClasses(File file);

}
