package com.automation.center.lighthouse.model;

import jakarta.persistence.*;

@Entity
public record Metric(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     Long id,
                     @Column(name = "name")
                     String name,
                     @Column(name = "code")
                     String code) {
}
