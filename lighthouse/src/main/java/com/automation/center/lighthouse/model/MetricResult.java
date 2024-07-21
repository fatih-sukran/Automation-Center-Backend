package com.automation.center.lighthouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "metric_result")
public class MetricResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private Metric metric;
    @ManyToOne
    @JoinColumn(name = "metric_url_id")
    private MetricUrl metricUrl;
    @ManyToOne
    @JoinColumn(name = "test_suite_id")
    private TestSuite testSuite;
}
