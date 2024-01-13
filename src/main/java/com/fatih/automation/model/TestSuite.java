package com.fatih.automation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Entity
@Table(name = "test_suite")
@Accessors(chain = true)
public class TestSuite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "test_suite_test_method",
            joinColumns = @JoinColumn(name = "test_suite_id"),
            inverseJoinColumns = @JoinColumn(name = "test_method_id"))
    List<TestMethod> testMethods;

    @ManyToMany
    @JoinTable(
            name = "test_suite_test_class",
            joinColumns = @JoinColumn(name = "test_suite_id"),
            inverseJoinColumns = @JoinColumn(name = "test_class_id"))
    List<TestClass> testClasses;
}
