package com.automation.center.lighthouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "metric_url")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public final class MetricUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "cron")
    private String cron;
    @ManyToMany
    @ToString.Exclude
    private List<Metric> metrics = new ArrayList<>();
}
