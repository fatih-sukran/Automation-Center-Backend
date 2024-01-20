package com.fatih.automation.jenkins.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JenkinsView {
    private String name;
    private List<JenkinsJob> jobs;
}
