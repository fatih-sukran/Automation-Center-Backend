package com.fatih.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

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
}
