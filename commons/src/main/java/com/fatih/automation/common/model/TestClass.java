package com.fatih.automation.common.model;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "test_class")
@Accessors(chain = true)
public class TestClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;

    @OneToMany(mappedBy = "testClass")
    private List<TestMethod> testMethods;

    @JsonIgnore
    @ManyToMany(mappedBy = "testClasses")
    private List<TestSuite> testSuites;
}
