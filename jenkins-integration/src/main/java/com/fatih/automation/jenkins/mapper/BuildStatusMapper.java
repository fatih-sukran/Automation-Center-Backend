package com.fatih.automation.jenkins.mapper;

import com.fatih.automation.common.enums.BuildStatus;
import com.offbytwo.jenkins.model.BuildResult;

public class BuildStatusMapper {

    public static BuildStatus map(BuildResult buildResult) {
        return switch (buildResult) {
            case SUCCESS, FAILURE, UNSTABLE -> BuildStatus.COMPLETED;
            case ABORTED, CANCELLED -> BuildStatus.ABORTED;
            case BUILDING -> BuildStatus.IN_PROGRESS;
            default -> BuildStatus.IN_QUEUE;
        };
    }
}
