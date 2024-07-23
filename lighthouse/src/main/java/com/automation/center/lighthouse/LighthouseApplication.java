package com.automation.center.lighthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.automation.center.lighthouse.model")
@SpringBootApplication
public class LighthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

}
