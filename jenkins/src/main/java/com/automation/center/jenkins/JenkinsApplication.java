package com.automation.center.jenkins;

import com.automation.center.jenkins.util.JenkinsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
    }

    @Bean
    public JenkinsManager jenkinsManager(@Value("${jenkins.username}") String username,
                                         @Value("${jenkins.password}") String password,
                                         @Value("${jenkins.url}") String url) {
        return new JenkinsManager(url, username, password);
    }

}
