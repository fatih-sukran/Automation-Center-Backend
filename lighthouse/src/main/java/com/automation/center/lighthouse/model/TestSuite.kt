package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "test_suite")
class TestSuite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var cron: String? = null

    @ManyToMany
    @JoinTable(
        name = "test_suite_metric",
        joinColumns = [JoinColumn(name = "test_suite_id")],
        inverseJoinColumns = [JoinColumn(name = "metric_id")]
    )
    val metrics: List<Metric> = ArrayList()

    @OneToMany
    @JoinColumn(name = "test_suite_id")
    val urls: List<SuiteUrl> = ArrayList()
}
