package com.automation.center.lighthouse.model

import jakarta.persistence.*

@Entity(name = "metric")
class Metric() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "code")
    var code: String? = null

    @ManyToMany(mappedBy = "metrics")
    var suits: List<TestSuite> = ArrayList()

    constructor(id: Long) : this() {
        this.id = id
    }
    constructor(name: String, code: String) : this() {
        this.name = name
        this.code = code
    }
}
