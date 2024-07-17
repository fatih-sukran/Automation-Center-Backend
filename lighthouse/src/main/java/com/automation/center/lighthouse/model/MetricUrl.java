package com.automation.center.lighthouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "url_and_metric", columnNames = {"url", "metric_id"}))
public final class MetricUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "metric_id")
    private Metric metric;
    @Column(name = "cron")
    private String cron;
}
