package com.fatih.automation.jenkins.tests;

import com.fatih.automation.jenkins.model.JenkinsJob;
import com.fatih.automation.jenkins.model.JenkinsView;
import com.fatih.automation.jenkins.utils.JenkinsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JenkinsUtilTests {

    @Test
    public void checkGetView() {
        var view = JenkinsUtil.getView("all");

        System.out.println("View: ");
        System.out.println(view);

        assertThat(view).isNotNull();
        assertThat(view.getName()).isEqualTo("all");
    }

    @Test
    public void checkGetAllViews() {
        var views = JenkinsUtil.getAllViews();

        System.out.println("Views: ");
        views.forEach(System.out::println);

        assertThat(views).isNotEmpty();
        assertThat(views.stream().map(JenkinsView::getName)).contains("all");
    }

    @Test
    public void checkGetAllJobs() {
        var jobs = JenkinsUtil.getAllJobs();

        System.out.println("Jobs: ");
        jobs.forEach(System.out::println);

        assertThat(jobs).isNotEmpty();
        assertThat(jobs.stream().map(JenkinsJob::getName)).contains("Automation Center");
    }
}
