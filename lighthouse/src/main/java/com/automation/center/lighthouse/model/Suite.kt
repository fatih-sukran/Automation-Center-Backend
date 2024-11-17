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
@Entity(name = "suite")
class Suite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var cron: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "suite_metric",
        joinColumns = [JoinColumn(name = "suite_id")],
        inverseJoinColumns = [JoinColumn(name = "metric_id")]
    )
    val metrics: List<Metric> = ArrayList()

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "suite", cascade = [CascadeType.ALL], orphanRemoval = true)
    val urls: List<Page> = ArrayList()
}
