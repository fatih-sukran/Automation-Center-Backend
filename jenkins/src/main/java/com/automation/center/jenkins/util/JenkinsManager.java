package com.automation.center.jenkins.util;

import com.offbytwo.jenkins.JenkinsServer;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URI;

@Getter
public class JenkinsManager {
    private final JenkinsServer server;

    public JenkinsManager(String username, String password, String url) {
        this.server = new JenkinsServer(URI.create("http://localhost:8081"),
                "admin", "admin");
    }

    @SneakyThrows
    public String getJobXml(String description, String script) {
        var file = new File(ClassLoader.getSystemResource("jenkins-xml/job.xml").toURI());
        var content = FileUtils.readFileToString(file, "UTF-8");
        return content.formatted(description, script);
    }
}
