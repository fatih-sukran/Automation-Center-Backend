package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity(name = "metric")
class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "code")
    var code: String? = null

    @ManyToMany(mappedBy = "metrics")
    var suiteItems: List<SuiteItem> = ArrayList()
}
