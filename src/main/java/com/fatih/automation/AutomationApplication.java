package com.fatih.automation;

import com.fatih.automation.controller.TestMethod;
import com.fatih.automation.controller.TestMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;

@SpringBootApplication
public class AutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).build();
	}

//	@Bean
//	CommandLineRunner init(TestMethodRepository repository) {
//		return args -> {
//			var PATH = "/Users/fatih.sukran/Downloads/api/src/test";
//			var file = new File(PATH);
//			var methods = Main.printPaths(file);
//
//			methods.forEach(method -> {
//				repository.save(new TestMethod(method.getName().asString()));
//			});
//		};
//	}
}
