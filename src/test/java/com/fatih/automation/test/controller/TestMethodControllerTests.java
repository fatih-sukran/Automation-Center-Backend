package com.fatih.automation.test.controller;

import com.fatih.automation.core.model.TestMethod;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMethodControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnTestMethodWhenAddedValid() {
        var testMethod = new TestMethod(null, "name", "description");
        var testMethodResponse = testRestTemplate.postForEntity("/testmethods", testMethod, String.class);
        assertThat(testMethodResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var documentContext = JsonPath.parse(testMethodResponse.getBody());
        String id = documentContext.read("$.id");
        assertThat(id).isNotNull();

        var name = documentContext.read("$.name");
        assertThat(name).isEqualTo(testMethod.name());

        var description = documentContext.read("$.description");
        assertThat(description).isEqualTo(testMethod.description());
    }

    @Test
    public void shouldGetAllTestMethods() {
        var testMethodsResponse = testRestTemplate.getForEntity("/testmethods", String.class);
        assertThat(testMethodsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(testMethodsResponse.getBody()).isNotNull();

        var documentContext = JsonPath.parse(testMethodsResponse.getBody());
        int length = documentContext.read("$.length()");
        assertThat(length).isEqualTo(0);
    }
}
