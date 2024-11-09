package com.automation.center.lighthouse;

import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.service.MetricService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("com.automation.center.lighthouse.model")
@SpringBootApplication
public class LighthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MetricService metricService) {
        return args -> {
            Metric metric = new Metric();
            metric.setName("FCP");
            metric.setCode("FCP");
            metricService.save(metric);
        };
    }

}
