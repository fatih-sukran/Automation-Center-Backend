package com.automation.center.jenkins.util;

import com.offbytwo.jenkins.JenkinsServer;
import lombok.Getter;

import java.net.URI;

@Getter
public class JenkinsManager {
    private final JenkinsServer server;

    public JenkinsManager(String username, String password, String url) {
        this.server = new JenkinsServer(URI.create("http://localhost:8081"),
                "admin", "admin");
    }
}
