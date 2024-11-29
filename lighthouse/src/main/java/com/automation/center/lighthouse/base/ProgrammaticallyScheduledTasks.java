package com.automation.center.lighthouse.base;

import com.automation.center.LightHouseApi;
import com.automation.center.lighthouse.dto.report.AddReportDto;
import com.automation.center.lighthouse.dto.result.AddResultDto;
import com.automation.center.lighthouse.dto.suite.SuiteDto;
import com.automation.center.lighthouse.service.ReportService;
import com.automation.center.lighthouse.service.ResultService;
import com.automation.center.lighthouse.service.SuiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProgrammaticallyScheduledTasks {
    private final TaskScheduler taskScheduler;
    private final SuiteService suiteService;
    private final ReportService reportService;
    private final ResultService resultService;
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();

    public void scheduleAllSuites() {
        cancelAllTasks();
        var suits = suiteService.findAll();
        suits.forEach(this::scheduleLighthouseTask);
    }

    public void cancelAllTasks() {
        scheduledTasks.forEach((k, v) -> v.cancel(false));
    }

    public void scheduleLighthouseTask(SuiteDto suiteDto) {
        checkTaskRunning(suiteDto);
        Runnable runnable = () -> {
            var addReportDto = new AddReportDto(suiteDto.getId(), LocalDateTime.now());
            var reportDto = reportService.save(addReportDto);
            var lighthouse = getLightHouseApi(suiteDto);
            log.info("#{} Suite Task Started", suiteDto.getId());
            var results = lighthouse.getResults();
            results.forEach(r -> {
                System.out.println("----------------------------------------------------");
                System.out.println(r.getPage().getUrl());
                r.getMetric().forEach((k, v) -> {
                    log.info("{} : {}", k.getName(), v);
                    var addResultDto = new AddResultDto(reportDto.getId(), r.getPage().getId(), k.getId(), v);
                    resultService.save(addResultDto);
                });
                System.out.println("----------------------------------------------------");
            });
            log.info("#{} Suite Task Finished", suiteDto.getId());
        };
        var schedule = taskScheduler.schedule(runnable, new CronTrigger("0 * * * * *"));
        scheduledTasks.put(suiteDto.getId(), schedule);
    }

    private static @NotNull LightHouseApi getLightHouseApi(SuiteDto suiteDto) {
        var pages = suiteDto.getPages();
        var metrics = suiteDto.getMetrics();
        return new LightHouseApi(pages, metrics);
    }

    private void checkTaskRunning(@NotNull SuiteDto suiteDto) {
        if (scheduledTasks.containsKey(suiteDto.getId())) {
            var future = scheduledTasks.get(suiteDto.getId());
            if (!future.isDone()) {
                future.cancel(false);
            }
            scheduledTasks.remove(suiteDto.getId());
        }
    }
}
