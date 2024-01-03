package com.fatih.automation.test.controller;

import com.fatih.automation.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMethodControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldGetAllTestMethods() {
        var testMethodsResponse = testRestTemplate.getForEntity("/testmethods", String.class);
        assertThat(testMethodsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(testMethodsResponse.getBody()).isNotNull();
    }
}
