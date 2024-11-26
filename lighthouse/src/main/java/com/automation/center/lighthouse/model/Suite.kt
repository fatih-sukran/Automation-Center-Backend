package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data
import lombok.RequiredArgsConstructor

@Data
@RequiredArgsConstructor
@Entity(name = "suite")
class Suite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var cron: String? = null

    @OneToMany(mappedBy = "suite", cascade = [CascadeType.ALL], orphanRemoval = true)
    var reports: List<Report> = ArrayList()

    @OneToMany(mappedBy = "suite", cascade = [CascadeType.ALL], orphanRemoval = true)
    var pages: List<Page> = ArrayList()

    @ManyToMany
    @JoinTable(
        name = "suite_metric",
        joinColumns = [JoinColumn(name = "suite_id")],
        inverseJoinColumns = [JoinColumn(name = "metric_id")]
    )
    var metrics: List<Metric> = ArrayList()
}
