package com.automation.center.lighthouse.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "url_and_metric", columnNames = {"url", "metric_id"}))
public record MetricUrl(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        Long id,
                        @Column(name = "url")
                        String url,
                        @ManyToOne
                        @JoinColumn(name = "metric_id")
                        Metric metric,
                        @Column(name = "cron")
                        String cron) {
}
