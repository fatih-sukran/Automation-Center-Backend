package com.fatih.automation.jenkins.utils;

import lombok.SneakyThrows;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.nio.file.Files;

public class ReadGitRepository {

    private static final String TOKEN = "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";
    private static final String REPOSITORY_URL = "https://github.com/fatih-sukran/automation-center-testing-project.git";
    private static final CredentialsProvider CREDENTIALS_PROVIDER = new UsernamePasswordCredentialsProvider(TOKEN, "");

    @SneakyThrows
    public static File cloneRepository() {
        var tempDir = Files.createTempDirectory("repo_").toFile();
        Git.cloneRepository()
                .setURI(REPOSITORY_URL)
                .setDirectory(tempDir)
                .setCredentialsProvider(CREDENTIALS_PROVIDER)
                .call()
                .close();
        return tempDir;
    }
}
