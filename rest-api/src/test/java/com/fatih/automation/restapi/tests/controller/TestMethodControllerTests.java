package com.fatih.automation.restapi.tests.controller;

import com.fatih.automation.common.model.TestMethod;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
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
        var testMethod = new TestMethod()
//                .setId(1L)
                .setName("name")
                .setDescription("description");
        testMethod.setId(1L);
        var testMethodResponse = testRestTemplate.postForEntity("/testmethods", testMethod, TestMethod.class);
//        assertThat(testMethodResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var response = testMethodResponse.getBody();
        assert response != null;
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(testMethod.getName());
        assertThat(response.getDescription()).isEqualTo(testMethod.getDescription());
    }

    @Test
    public void shouldGetAllTestMethods() {
        var testMethodsResponse = testRestTemplate.getForEntity("/testmethods", String.class);
        Assertions.assertThat(testMethodsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(testMethodsResponse.getBody()).isNotNull();

        var documentContext = JsonPath.parse(testMethodsResponse.getBody());
        int length = documentContext.read("$.length()");
        Assertions.assertThat(length).isEqualTo(0);
    }
}
