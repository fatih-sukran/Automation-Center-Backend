package com.fatih.automation.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Entity
@Table(name = "test_method")
@Accessors(chain = true)
public class TestMethod {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "test_class_id")
        private TestClass testClass;

        @JsonIgnore
        @ManyToMany(mappedBy = "testMethods")
        private List<TestSuite> testSuites;
}
