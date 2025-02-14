package com.automation.center.lighthouse;

import com.automation.center.lighthouse.base.ProgrammaticallyScheduledTasks;
import com.automation.center.lighthouse.dto.metric.AddMetricDto;
import com.automation.center.lighthouse.dto.page.AddPageDto;
import com.automation.center.lighthouse.dto.suite.AddSuiteDto;
import com.automation.center.lighthouse.service.MetricService;
import com.automation.center.lighthouse.service.PageService;
import com.automation.center.lighthouse.service.SuiteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableScheduling
@EnableJpaRepositories
@ComponentScan("com.automation.center.lighthouse")
@EntityScan("com.automation.center.lighthouse.model")
@SpringBootApplication
public class LighthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LighthouseApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean
    CommandLineRunner initScheduledTasks(SuiteService suiteService, PageService pageService, MetricService metricService, ProgrammaticallyScheduledTasks programmaticallyScheduledTasks) {
        return args -> {

            // Save All Metrics
            var metrics = List.of(new AddMetricDto("Total Blocking Time", "TBT", "lighthouseResult.audits.total-blocking-time.numericValue"),
                    new AddMetricDto("Speed Index", "SI", "lighthouseResult.audits.speed-index.numericValue"),
                    new AddMetricDto("Cumulative Layout Shift", "CLS", "loadingExperience.metrics.CUMULATIVE_LAYOUT_SHIFT_SCORE.percentile"),
                    new AddMetricDto("Largest Contentful Paint", "LCP", "loadingExperience.metrics.LARGEST_CONTENTFUL_PAINT_MS.percentile"),
                    new AddMetricDto("Time to First Byte", "TFB", "loadingExperience.metrics.EXPERIMENTAL_TIME_TO_FIRST_BYTE.percentile"),
                    new AddMetricDto("Performance Score", "PS", "lighthouseResult.categories.performance.score"));
            var metricDtos = metrics.stream().map(metricService::save).toList();

            // Save All Suites
            var suits = List.of(
                    new AddSuiteDto("HangiKredi", "HangiKredi Linkleri", "*/3 * * * *"),
                    new AddSuiteDto("Enuygun", "Enuygun Linkleri", "*/2 * * * *"));
            var suiteDtos = suits.stream().map(suiteService::save).toList();

            // Add All Metrics To Suites
            suiteDtos.forEach(s -> metricDtos.forEach(m -> suiteService.addMetricToSuite(s.getId(), m.getId())));

            // Save All Pages
            var pages = List.of(
                    new AddPageDto(suiteDtos.get(0).getId(), "https://www.hangikredi.com/", "HangiKredi Home Page"),
                    new AddPageDto(suiteDtos.get(1).getId(), "https://www.enuygun.com/", "Enuygun Home Page")
            );
            pages.forEach(pageService::save);

            programmaticallyScheduledTasks.scheduleAllSuites();
        };
    }
}
