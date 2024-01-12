package com.fatih.automation;

import com.fatih.automation.repositories.TestClassRepository;
import com.fatih.automation.repositories.TestMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TestClassRepository testClassRepository,
						   TestMethodRepository testMethodRepository) {
		return args -> {
			initTestClasses(testClassRepository);
			initTestMethods(testClassRepository, testMethodRepository);
		};
	}

	private void initTestClasses(TestClassRepository testClassRepository) {
		var PATH = "/Users/fatih.sukran/Downloads/api/src/test";
		var file = new File(PATH);
		var classes = Main.findTestClasses(file);

		classes.forEach(testClass -> {
			if (!testClassRepository.existsByPath(testClass.getPath())) {
				testClassRepository.save(testClass);
			}
		});
	}

	private void initTestMethods(TestClassRepository testClassRepository,
								 TestMethodRepository testMethodRepository) {
		var testClasses = testClassRepository.findAll();

		for (var testClass : testClasses) {
			var testMethods = Main.findTestMethods(testClass.getPath());
			for (var testMethod : testMethods) {
				if (!testMethodRepository.existsByNameAndTestClassId(testMethod.getName(), testClass.getId())) {
					testMethod.setTestClass(testClass);
					testMethodRepository.save(testMethod);
				}
			}
		}
	}
}
