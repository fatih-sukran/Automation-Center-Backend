package com.automation.center.lighthouse;

import com.automation.center.lighthouse.model.Metric;
import com.automation.center.lighthouse.service.MetricService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@EntityScan("com.automation.center.lighthouse.model")
@SpringBootApplication
public class LighthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(MetricService metricService) {
        return args -> {
            metricService.deleteAll();
            List<Metric> metrics = List.of(
                    new Metric("First Contentful Paint", "FCP"),
                    new Metric("Speed Index", "SI"),
                    new Metric("Largest Contentful Paint", "LCP"),
                    new Metric("Time to Interactive", "TTI"),
                    new Metric("Total Blocking Time", "TBT"),
                    new Metric("Cumulative Layout Shift", "CLS"),
                    new Metric("Semantic HTML Usage", "Accessibility-Semantic-HTML"),
                    new Metric("Color Contrast", "Accessibility-Color-Contrast"),
                    new Metric("Alt Texts", "Accessibility-Alt-Texts"),
                    new Metric("Form Labels", "Accessibility-Form-Labels"),
                    new Metric("Aria Attributes", "Accessibility-Aria"),
                    new Metric("HTTPS Usage", "BestPractices-HTTPS"),
                    new Metric("Security Vulnerabilities", "BestPractices-Security"),
                    new Metric("Outdated JS Libraries", "BestPractices-OldJS"),
                    new Metric("Content Security Policy", "BestPractices-CSP"),
                    new Metric("Meta Descriptions", "SEO-Meta"),
                    new Metric("Title Tags", "SEO-Title-Tags"),
                    new Metric("Mobile Friendly", "SEO-Mobile-Friendly"),
                    new Metric("Robots.txt Presence", "SEO-Robots-Txt"),
                    new Metric("Service Workers", "PWA-Service-Workers"),
                    new Metric("Offline Support", "PWA-Offline-Support"),
                    new Metric("Manifest File", "PWA-Manifest")
            );
            metricService.saveAll(metrics);
        };
    }

}
