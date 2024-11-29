package com.automation.center.lighthouse.model

import jakarta.persistence.*

@Entity
@Table(name = "metric")
class Metric() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var code: String? = null
    var jsonPath: String? = null


    @OneToMany(mappedBy = "metric", cascade = [CascadeType.ALL], orphanRemoval = true)
    var results: List<Result> = ArrayList()


    @ManyToMany
    @JoinTable(
        name = "suite_metric",
        joinColumns = [JoinColumn(name = "metric_id")],
        inverseJoinColumns = [JoinColumn(name = "suite_id")]
    )
    var suites: List<Suite> = ArrayList()

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(name: String, code: String) : this() {
        this.name = name
        this.code = code
    }
}
