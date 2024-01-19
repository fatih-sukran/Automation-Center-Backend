package com.fatih.automation.restapi;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.jenkins.Main;
import com.fatih.automation.restapi.repositories.TestClassRepository;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;

@EntityScan(basePackages = {"com.fatih.automation.common.model", "com.fatih.automation.restapi"})
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

		classes.forEach(clazz -> {
			if (!testClassRepository.existsByPath(clazz.getPath())) {
				var testClass = new TestClass()
						.setName(clazz.getName())
						.setPath(clazz.getPath());
				// TODO: add test methods
				testClassRepository.save(testClass);
			}
		});
	}

	private void initTestMethods(TestClassRepository testClassRepository,
								 TestMethodRepository testMethodRepository) {
		var testClasses = testClassRepository.findAll();

		for (var testClass : testClasses) {
			var methods = Main.findTestMethods(testClass.getPath());
			for (var method : methods) {
				if (!testMethodRepository.existsByNameAndTestClassId(method.getName(), testClass.getId())) {
//					var testMethod = new TestM
//					testMethodRepository.save(method);
				}
			}
		}
	}
}
