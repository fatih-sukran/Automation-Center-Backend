package com.automation.center.lighthouse.base;

import com.automation.center.lighthouse.dto.suite.SuiteDto;
import com.automation.center.lighthouse.service.SuiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProgrammaticallyScheduledTasks {
    private final TaskScheduler taskScheduler;
    private final SuiteService suiteService;
    private final JenkinsUtil jenkinsUtil;
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();
    private final Map<Long, AtomicInteger> counter = new HashMap<>();

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
        if (counter.containsKey(suiteDto.getId())) {
            counter.get(suiteDto.getId()).incrementAndGet();
        } else {
            counter.put(suiteDto.getId(), new AtomicInteger(1));
        }
        Runnable runnable = () -> {
            var suiteId = String.valueOf(suiteDto.getId());
            log.info("#{} Suite Task Started - Counter: {}", suiteDto.getId(), counter.get(suiteDto.getId()));
            var job = jenkinsUtil.getJob("Lighthouse");
            jenkinsUtil.buildJob(job, Map.of("SUITE_ID", suiteId));
        };
        var schedule = taskScheduler.schedule(runnable, new CronTrigger("0 " + suiteDto.getCron()));
        scheduledTasks.put(suiteDto.getId(), schedule);
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
