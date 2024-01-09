package com.fatih.automation;

import com.fatih.automation.model.TestMethod;
import com.fatih.automation.repositories.TestMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TestMethodRepository repository) {
		return args -> {
			var PATH = "/Users/fatih.sukran/Downloads/api/src/test";
			var file = new File(PATH);
			var methods = Main.printPaths(file);

			methods.forEach(testMethod -> {
				if (!repository.existsByNameAndClassName(testMethod.getName(), testMethod.getClassName())) {
					repository.save(testMethod);
				}
			});
		};
	}
}
