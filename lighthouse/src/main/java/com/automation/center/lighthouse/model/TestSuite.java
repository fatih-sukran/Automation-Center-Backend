package com.automation.center.lighthouse.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public record TestSuite(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        Long id,
                        @Column(name = "start_date")
                        LocalDateTime startDate,
                        @Column(name = "end_date")
                        LocalDateTime endDate) {
}
