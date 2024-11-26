package com.automation.center.lighthouse.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "report")
class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var date: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "suite_id")
    var suite: Suite? = null

    @OneToMany(mappedBy = "report", cascade = [CascadeType.ALL], orphanRemoval = true)
    var results: List<Result> = ArrayList()
}
