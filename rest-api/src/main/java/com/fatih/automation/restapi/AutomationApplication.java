package com.fatih.automation.restapi;

import com.fatih.automation.common.model.TestMethod;
import com.fatih.automation.jenkins.Main;
import com.fatih.automation.jenkins.utils.ReadGitRepository;
import com.fatih.automation.restapi.repositories.TestClassRepository;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@EntityScan(basePackages = {"com.fatih.automation"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class, args);
    }

    /**
     * initialize test classes and test methods from given path
     */
    @Bean
    CommandLineRunner init(TestClassRepository testClassRepository,
                           TestMethodRepository testMethodRepository) {
        return args -> {
            initTestClasses(testClassRepository);
            initTestMethods(testClassRepository, testMethodRepository);
        };
    }

    /**
     * initialize test classes from given path
     */
    private void initTestClasses(TestClassRepository testClassRepository) {
        var file = ReadGitRepository.cloneRepository();

        // find all test classes in the given directory
        // save them to the database if they don't exist in the database
        Main.findTestClasses(file).stream()
                .filter(clazz -> !testClassRepository.existsByPackageName(clazz.getPackageName()))
                .forEach(testClassRepository::save);

    }

    /**
     * initialize test methods
     */
    private void initTestMethods(TestClassRepository testClassRepository,
                                 TestMethodRepository testMethodRepository) {
        var testClasses = testClassRepository.findAll();

        for (var testClass : testClasses) {
            // get existing method names from test class
            var existingMethodNames = testClass.getTestMethods().stream()
                    .map(TestMethod::getName)
                    .collect(Collectors.toSet());

            var file = ReadGitRepository.cloneRepository();
            // find all test methods in the given directory
            // add them to the test class if they don't exist in the database
            Main.findTestMethods(file).stream()
                    .filter(method -> !existingMethodNames.contains(method.getName()))
                    .map(testMethod -> testMethod.setTestClass(testClass))
                    .forEach(testMethodRepository::save);

            // save the test class
            testClassRepository.save(testClass);
        }
    }

}
