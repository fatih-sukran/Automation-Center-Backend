package com.fatih.automation.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TestMethod {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public TestMethod(String name) {
        this.name = name;
    }
}
