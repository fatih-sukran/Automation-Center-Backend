package com.automation.center.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.automation.center.jenkins")
@EntityScan("com.automation.center.jenkins")
@SpringBootApplication
public class JenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
    }

}
