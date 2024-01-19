package com.fatih.automation.restapi.tests.repository;

import com.fatih.automation.common.model.TestMethod;
import com.fatih.automation.restapi.repositories.TestMethodRepository;
import com.fatih.automation.restapi.services.TestMethodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestMethodServiceTests {

    @Mock
    private TestMethodRepository testMethodRepository;

    @InjectMocks
    private TestMethodService testMethodService;

    @Test
    public void shouldSaveTestMethod() {
        var testMethod = new TestMethod()
                .setName("name")
                .setDescription("description");

        Mockito.when(testMethodRepository.save(testMethod)).thenReturn(testMethod);

        var savedTestMethod = testMethodService.save(testMethod);

        assertThat(savedTestMethod).isEqualTo(testMethod);
        Mockito.verify(testMethodRepository).save(testMethod);
    }

    @Test
    public void shouldThrowErrorWhenTestMethodNull() {
        Mockito.when(testMethodRepository.save(null)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> testMethodService.save(null));
    }

    @Test
    public void shouldGetAllTestMethods() {
        var testMethod1 = new TestMethod()
                .setId(1L)
                .setName("name1")
                .setDescription("description1");
        var testMethod2 = new TestMethod()
                .setId(2L)
                .setName("name2")
                .setDescription("description2");
        var testMethods = List.of(testMethod1, testMethod2);
        Mockito.when(testMethodRepository.findAll()).thenReturn(testMethods);

        var testMethodsResponse = testMethodService.findAll();

        assertThat(testMethodsResponse).isEqualTo(testMethods);
        Mockito.verify(testMethodRepository).findAll();
    }

    @Test
    public void shouldGetTestMethodById() {
        var testMethod = new TestMethod()
                .setId(1L)
                .setName("name")
                .setDescription("description");
        Mockito.when(testMethodRepository.findById(1L)).thenReturn(Optional.of(testMethod));

        var testMethodResponse = testMethodService.findById(1L);

        assertThat(testMethodResponse.isPresent()).isTrue();
        assertThat(testMethodResponse.get()).isEqualTo(testMethod);
        Mockito.verify(testMethodRepository).findById(1L);
    }

    @Test
    public void shouldReturnOptionalEmptyWhenTestMethodNotFound() {
        var testMethod = new TestMethod()
                .setId(1L)
                .setName("name")
                .setDescription("description");
        Mockito.when(testMethodRepository.findById(-1L)).thenReturn(Optional.empty());

        var actualTestMethod = testMethodService.findById(-1L);

        assertThat(actualTestMethod.isPresent()).isFalse();
        Mockito.verify(testMethodRepository).findById(-1L);
    }

    @Test
    public void shouldThrowErrorWhenIdNull() {
        var testMethod = new TestMethod()
                .setName("name")
                .setDescription("description");
        Mockito.when(testMethodRepository.findById(testMethod.getId())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> testMethodService.findById(null));

        Mockito.verify(testMethodRepository).findById(testMethod.getId());
    }
}
