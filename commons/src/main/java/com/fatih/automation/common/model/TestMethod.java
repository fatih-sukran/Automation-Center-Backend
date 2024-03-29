package com.fatih.automation.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "test_method")
@Accessors(chain = true)
public class TestMethod extends BaseEntity {
        private String name;
        private String description;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "test_class_id")
        private TestClass testClass;

        @JsonIgnore
        @ManyToMany(mappedBy = "testMethods")
        private List<TestSuite> testSuites;
}
