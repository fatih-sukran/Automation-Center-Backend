package com.automation.center.lighthouse.model;

import jakarta.persistence.*;

@Entity
public record ResultHistory(@Id
                            @GeneratedValue(strategy = GenerationType.IDENTITY)
                            Long id,
                            @ManyToOne
                            @JoinColumn(name = "url_id")
                            MetricUrl url,
                            String value) {

}
