package com.fatih.automation.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatih.automation.common.model.build.ClassBuild;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "test_class")
@Accessors(chain = true)
public class TestClass extends BaseEntity {
    private String name;
    private String packageName;

    @OneToMany(mappedBy = "testClass", fetch = FetchType.EAGER)
    private List<TestMethod> testMethods;

    @JsonIgnore
    @ManyToMany(mappedBy = "testClasses")
    private List<TestSuite> testSuites;

    @JsonIgnore
    @OneToMany(mappedBy = "testClass")
    private List<ClassBuild> classBuilds;
}
