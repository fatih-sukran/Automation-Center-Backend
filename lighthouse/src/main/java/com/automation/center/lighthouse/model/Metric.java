package com.automation.center.lighthouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "metric")
@Getter
@Setter
@ToString
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "metrics")
    @ToString.Exclude
    private List<SuiteItem> suiteItems = new ArrayList<>();
}
