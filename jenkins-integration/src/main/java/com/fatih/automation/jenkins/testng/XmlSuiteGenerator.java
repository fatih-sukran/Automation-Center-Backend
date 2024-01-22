package com.fatih.automation.jenkins.testng;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.common.model.TestMethod;
import org.assertj.core.util.Sets;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;
import java.util.Set;

public class XmlSuiteGenerator {
    private final Set<TestMethod> testMethods = Sets.set();
    private final Set<TestClass> testClasses = Sets.set();

    public void addTestMethod(TestMethod... testMethod) {
        testMethods.addAll(Set.of(testMethod));
    }

    public XmlSuiteGenerator addTestClass(TestClass... testClass) {
        testClasses.addAll(Set.of(testClass));
        return this;
    }

    public String generate() {
        var xmlSuite = new XmlSuite();
        xmlSuite.setName("Automation Center");
        xmlSuite.setParallel(XmlSuite.ParallelMode.METHODS);

        var xmlTest = new XmlTest(xmlSuite);

        var xmlClasses = testClasses.stream()
                .map(testClass -> new XmlClass(testClass.getPackageName() + "." + testClass.getName(), false))
                .toList();
        xmlTest.setXmlClasses(xmlClasses);
        xmlSuite.setTests(List.of(xmlTest));

        return xmlSuite.toXml();
    }
}
